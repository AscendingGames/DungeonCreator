package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.Room
import com.ascending.games.id1.model.board.RoomElement
import com.ascending.games.engine.model.data.ObservableList
import com.ascending.games.engine.model.geometry.Coord2

class MockRoomFactory : IRoomFactory {
    override fun createRoom(): Room {
        return Room(ObservableList(mutableListOf(RoomElement(Coord2(0, 0)))))
    }
}