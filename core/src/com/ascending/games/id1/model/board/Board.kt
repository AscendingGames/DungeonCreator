package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.data.ObservableList
import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.geometry.Direction4
import com.ascending.games.lib.model.pathfinding.IGraph

class Board(val width : Int, val height : Int) : IGraph<RoomElement> {
    val hero : Hero = Hero()
    val rooms = ObservableList(mutableListOf<Room>())

    override fun getNodes(): List<RoomElement> {
       return rooms.flatMap { it.roomElements }
    }

    override fun getNeighbours(node  : RoomElement): List<RoomElement> {
        val neighbouringRoomElements = mutableListOf<RoomElement>()
        for (direction in Direction4.values().filter { node.isOpen(it) }) {
            val offset = direction.toOffset()
            val coord = node.boardCoord.add(offset)

            val roomElementOther = getRoomElementAt(coord)
            if (roomElementOther != null && roomElementOther.isOpen(direction.opposite())) {
                neighbouringRoomElements.add(roomElementOther)
            }
        }

        return neighbouringRoomElements
    }

    fun getNeighbours(room : Room) : Set<Room> {
        return room.roomElements.flatMap { roomElement -> getNeighbours(roomElement).map { it.room }.filter { it != room } }.toSet()
    }

    fun haveRoomsFallen() : Boolean {
        return rooms.all { hasRoomFallen(it) }
    }

    fun hasRoomFallen(room : Room) : Boolean {
        return room.roomElements.any { it.boardY <= 0 || existsRoomBelow(it) }
    }

    fun existsRoomBelow(roomElement : RoomElement) : Boolean {
        val roomCoord = roomElement.boardCoord
        val roomBelow = getRoomAt(Coord2(roomCoord.x, roomCoord.y - 1))
        return roomBelow != null && roomBelow != roomElement.room && roomBelow.position.y.toDouble() == Math.ceil(roomBelow.position.y.toDouble())
    }

    fun getRoomElementsAt(position : Coord2) : List<RoomElement> {
        return rooms.flatMap { room -> room.roomElements.filter { it.boardCoord == position } }
    }

    fun getRoomElementAt(position : Coord2) : RoomElement? {
        return getRoomElementsAt(position).getOrNull(0)
    }

    fun getRoomAt(position : Coord2) : Room? {
        val roomElement = getRoomElementAt(position)
        roomElement ?: return null

        return roomElement.room
    }

    fun isRoomOverlapping(room : Room) : Boolean {
        return room.roomElements.any { roomElement -> getRoomElementsAt(roomElement.boardCoord).filter { it != roomElement }.isNotEmpty() }
    }

    fun isRoomInBounds(room : Room) : Boolean {
        return room.roomElements.none { it.boardX < 0 || it.boardY < 0 || it.boardX >= width || it.boardY >= height }
    }

    fun openWallsNeighbouringDoors(room : Room) {
        val connectedRooms = mutableSetOf<Room>()
        for (roomElement in room.roomElements) {
            val wallsToOpen = getWallsToOpen(roomElement)
            wallsToOpen.forEach {
                connectedRooms.add(it.roomElement.room)
                it.roomElement.walls -= it
            }
        }
    }

    fun getWallsToOpen(roomElement : RoomElement) : List<Wall> {
        val wallsToOpen = mutableListOf<Wall>()
        for (wall in roomElement.walls) {
            val coordOther = roomElement.boardCoord.add(wall.direction.toOffset())
            val roomElementOther = getRoomElementAt(coordOther)
            if (roomElementOther != null && hasRoomFallen(roomElementOther.room)) {
                val wallOther = roomElementOther.walls.find { it.direction == wall.direction.opposite() }
                wallsToOpen.addAll(getWallsToOpen(wall, wallOther))
            }
        }
        return wallsToOpen
    }

    private fun getWallsToOpen(wall : Wall?, wallOther : Wall?) : List<Wall> {
        if (wall != null && wallOther != null) {
            if (wall.wallState == WallState.DOOR || wallOther.wallState == WallState.DOOR) {
                return listOf(wall, wallOther)
            }
        }

        return emptyList<Wall>()
    }
}