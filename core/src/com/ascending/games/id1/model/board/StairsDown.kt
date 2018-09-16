package com.ascending.games.id1.model.board

class StairsDown(roomElement : RoomElement) : ARoomContent() {
    init {
        this.roomElement = roomElement
        this.roomElement.roomContents.add(this)
    }
}