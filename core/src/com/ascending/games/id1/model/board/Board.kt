package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.geometry.Direction4
import com.ascending.games.lib.model.pathfinding.IGraph

class Board(val width : Int, val height : Int) : IGraph<RoomElement> {
    override fun getNodes(): List<RoomElement> {
       return rooms.flatMap { it.roomElements }
    }

    override fun getNeighbours(node  : RoomElement): List<RoomElement> {
        val neighbouringRoomElements = mutableListOf<RoomElement>()
        for (direction in Direction4.values()) {
            if (node.isOpen(direction)) {
                val offset = direction.toOffset()
                val coord = node.getBoardCoord().add(offset)

                val roomElementOther = getRoomElementAt(coord)
                if (roomElementOther != null && roomElementOther.isOpen(direction.opposite())) {
                    neighbouringRoomElements.add(roomElementOther)
                }
            }
        }

        return neighbouringRoomElements
    }

    fun getNeighbours(room : Room) : Set<Room> {
        return room.roomElements.flatMap { roomElement -> getNeighbours(roomElement).map { it.room }.filter { it != room } }.toSet()
    }

    var rooms = emptyList<Room>()

    fun haveRoomsFallen() : Boolean {
        return rooms.all { hasRoomFallen(it) }
    }

    fun hasRoomFallen(room : Room) : Boolean {
        for (roomElement in room.roomElements) {
            if (roomElement.getBoardY() <= 0) {
                return true
            } else {
                val roomCoord = roomElement.getBoardCoord()
                val roomBelow = getRoomAt(Coord2(roomCoord.x, roomCoord.y - 1))
                if (roomBelow != null && roomBelow != room && roomBelow.position.y.toDouble() == Math.ceil(roomBelow.position.y.toDouble())) return true
            }
        }

        return false
    }

    fun getRoomElementsAt(position : Coord2) : List<RoomElement> {
        return rooms.flatMap { room -> room.roomElements.filter { it.getBoardCoord() == position } }
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
        return !room.roomElements.none { getRoomElementsAt(it.getBoardCoord()).size > 1 }
    }

    fun isRoomInBounds(room : Room) : Boolean {
        return room.roomElements.none { it.getBoardX() < 0 || it.getBoardY() < 0 || it.getBoardX() >= width || it.getBoardY() >= height }
    }

    private fun getClearedElements(row : Int) : List<RoomElement> {
        var clearedElements = emptyList<RoomElement>()

        for (x in 0 until width) {
            val roomElement = getRoomElementAt(Coord2(x, row))
            roomElement ?: return emptyList()

            if (!hasRoomFallen(roomElement.room)) {
                return emptyList()
            }

            clearedElements += roomElement
        }

        return clearedElements
    }

    fun clearRowIfFull(row : Int) : Boolean {
        val clearedElements = getClearedElements(row)
        for (roomElement in clearedElements) {
            val room = roomElement.room
            room.roomElements -= roomElement
            if (room.roomElements.isEmpty()) {
                rooms -= room
            }
        }

        return !clearedElements.isEmpty()
    }

    fun openWallsNeighbouringDoors(room: Room) {
        for (roomElement in room.roomElements) {
            val wallsToOpen = getWallsToOpen(roomElement)

            for (wall in wallsToOpen) {
                wall.roomElement.walls -= wall
            }
        }
    }

    private fun getWallsToOpen(roomElement : RoomElement) : List<Wall> {
        val wallsToOpen = mutableListOf<Wall>()
        for (wall in roomElement.walls) {
            val coordOther = roomElement.getBoardCoord().add(wall.direction.toOffset())
            val roomElementOther = getRoomElementAt(coordOther)
            if (roomElementOther != null) {
                val wallOther = roomElementOther.walls.find { it.direction == wall.direction.opposite() }
                if (wallOther != null) {
                    wallsToOpen.addAll(getWallsToOpen(wall, wallOther))
                }
            }
        }
        return wallsToOpen
    }

    private fun getWallsToOpen(wall : Wall, wallOther : Wall) : List<Wall> {
        if (wall.wallState == WallState.DOOR || wallOther.wallState == WallState.DOOR) {
            return listOf(wall, wallOther)
        }

        return emptyList<Wall>()
    }
}