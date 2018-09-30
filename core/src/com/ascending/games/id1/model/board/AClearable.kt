package com.ascending.games.id1.model.board

abstract class AClearable : ARoomContent() {
    fun spawn(roomElement: RoomElement) {
        this.roomElement = roomElement
        this.roomElement.clearables.add(this)
    }
}