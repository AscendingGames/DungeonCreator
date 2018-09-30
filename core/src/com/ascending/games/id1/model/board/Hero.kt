package com.ascending.games.id1.model.board

class Hero() : ARoomContent() {
    var spawned = false

    fun spawn(board : Board) {
        spawned = true

        val room = board.rooms[0]
        roomElement = room.roomElements.filter { roomElement -> room.roomElements.all { roomElement.roomRelativeCoord.y <= it.roomRelativeCoord.y } }.first()
    }


}