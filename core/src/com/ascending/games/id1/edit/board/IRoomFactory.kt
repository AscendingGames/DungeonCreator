package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.Room

interface IRoomFactory {
    fun createRoom() : Room;
}