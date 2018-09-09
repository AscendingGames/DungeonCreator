package com.ascending.games.id1.model.board

import com.badlogic.gdx.math.Vector2

class Room(var roomElements : List<RoomElement> = emptyList(), var position : Vector2 = Vector2.Zero.cpy()) {

    init {
        roomElements.forEach { it.room = this }
    }

    fun rotate() : Room {
        roomElements.forEach { it.rotate() }
        return this
    }
}