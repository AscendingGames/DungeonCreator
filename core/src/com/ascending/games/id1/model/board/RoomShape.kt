package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.geometry.Direction4

data class RoomShape(val roomElements : List<RoomElement>) {
    fun createRoom() : Room {
        val copiedRoomElements = roomElements.map { it -> it.copy() }
        val createdRoom = Room(copiedRoomElements)
        for (roomElement in createdRoom.roomElements) {
            roomElement.walls += Direction4.values()
                    .filter{ dir -> createdRoom.roomElements.none { roomElement.position.add(dir.toOffset()) == it.position } }
                    .map { Wall(roomElement, it, WallState.CLOSED) }
        }

        return createdRoom
    }
}