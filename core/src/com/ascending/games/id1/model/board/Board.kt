package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.geometry.Coord2
import com.sun.org.apache.xpath.internal.operations.Bool

class Board(val width : Int, val height : Int) {
    var rooms = emptyList<Room>()

    fun haveRoomsFallen() : Boolean {
        return rooms.all { hasRoomFallen(it) }
    }

    fun hasRoomFallen(room : Room) : Boolean {
        for (roomElement in room.roomElements) {
            if (getBoardY( roomElement) > 0) {
                val roomCoord = getBoardCoord(roomElement)
                val roomBelow = getRoomAt(Coord2(roomCoord.x, roomCoord.y - 1))
                roomBelow ?: return false
            }
        }

        return true
    }

    fun getRoomElementAt(position : Coord2) : RoomElement? {
        for (room in rooms) {
            for (roomElement in room.roomElements) {
                if (getBoardCoord(roomElement) == position) {
                    return roomElement
                }
            }
        }
        return null
    }

    fun getRoomAt(position : Coord2) : Room? {
        val roomElement = getRoomElementAt(position)
        roomElement ?: return null

        return roomElement.room
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

    fun getBoardCoord(roomElement : RoomElement) : Coord2 {
        return Coord2(getBoardX(roomElement), getBoardY(roomElement))
    }

    fun getBoardX(roomElement : RoomElement) : Int {
        return Math.ceil(roomElement.room.position.x.toDouble() + roomElement.position.x).toInt()
    }
    fun getBoardY(roomElement : RoomElement) : Int {
        return Math.ceil(roomElement.room.position.y.toDouble() + roomElement.position.y).toInt()
    }
}