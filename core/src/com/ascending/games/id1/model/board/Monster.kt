package com.ascending.games.id1.model.board

class Monster(roomElement : RoomElement) : ARoomContent() {
    init {
        this.roomElement = roomElement
        this.roomElement.roomContents.add(this)
    }
}