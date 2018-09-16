package com.ascending.games.id1.model.board

class Crystal(val type : Crystal.Type, roomElement : RoomElement) : ARoomContent() {
    enum class Type {
        HEALING, ATTACK
    }

    init {
        this.roomElement = roomElement
        this.roomElement.roomContents.add(this)
    }
}