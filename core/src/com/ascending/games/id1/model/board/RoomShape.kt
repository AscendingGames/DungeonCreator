package com.ascending.games.id1.model.board

data class RoomShape(val roomElements : List<RoomElement>) {
    fun createRoom() : Room {
        val copiedRoomElements = roomElements.map { it -> it.copy() }
        return Room(copiedRoomElements)
    }
}