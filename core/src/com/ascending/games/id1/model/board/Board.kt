package com.ascending.games.id1.model.board

class Board(val width : Int, val height : Int) {
    var rooms = emptyList<Room>()

    fun roomsHaveFallen(): Boolean {
        for (room in rooms) {
            for (roomElement in room.roomElements) {
                if (getBoardY(room, roomElement) > 0) {
                    val roomBelow = getRoomAt(getBoardX(room, roomElement), getBoardY(room, roomElement) - 1)
                    roomBelow ?: return false
                }
            }
        }

        return true
    }

    fun getRoomAt(x: Int, y: Int): Room? {
        for (room in rooms) {
            for (roomElement in room.roomElements) {
                if (getBoardX(room, roomElement) == x && getBoardY(room, roomElement) == y) {
                    return room
                }
            }
        }

        return null
    }

    fun getBoardX(room : Room, roomElement : RoomElement) : Int {
        return Math.ceil(room.x + roomElement.x).toInt()
    }
    fun getBoardY(room : Room, roomElement : RoomElement) : Int {
        return Math.ceil(room.y + roomElement.y).toInt()
    }
}