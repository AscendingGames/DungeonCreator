package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.Room

class DefaultRoomFactory(private val roomShapes : List<Room>) : IRoomFactory{
    override fun createRoom(): Room {
        val newRoom = roomShapes.shuffled().last().copy()
        return newRoom
    }
}