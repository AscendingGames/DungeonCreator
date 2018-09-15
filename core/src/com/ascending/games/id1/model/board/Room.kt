package com.ascending.games.id1.model.board

import com.badlogic.gdx.math.Vector2

class Room(val roomElements : MutableList<RoomElement> = mutableListOf(), var position : Vector2 = Vector2.Zero.cpy()) {
    val allRoomContents : List<ARoomContent>
        get() = roomElements.flatMap { it.roomContents }
    val isCleared : Boolean
        get() = allRoomContents.isEmpty()

    init {
        roomElements.forEach { it.room = this }
    }

    fun rotate() : Room {
        roomElements.forEach { it.rotate() }
        return this
    }
}