package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.Room
import com.ascending.games.id1.model.board.RoomElement
import com.ascending.games.id1.model.board.RoomShape
import com.ascending.games.lib.model.geometry.Coord2

class DefaultRoomFactory(private val roomShapes : List<RoomShape>) : IRoomFactory{

    companion object {
        fun createDefaultRoomFactory() : DefaultRoomFactory {
            val roomShapes = listOf<RoomShape>(
                    RoomShape(listOf(RoomElement(Coord2(0,0)), RoomElement(Coord2(0,-1)), RoomElement(Coord2(0,-2)))),
                    RoomShape(listOf(RoomElement(Coord2(0,0)), RoomElement(Coord2(0,-1)), RoomElement(Coord2(-1,-1)), RoomElement(Coord2(1,0)))),
                    RoomShape(listOf(RoomElement(Coord2(0,0)), RoomElement(Coord2(0,-1)), RoomElement(Coord2(0,-2)), RoomElement(Coord2(1,0)))),
                    RoomShape(listOf(RoomElement(Coord2(0,0)), RoomElement(Coord2(0,-1)), RoomElement(Coord2(1,0)), RoomElement(Coord2(1,-1)), RoomElement(Coord2(2,0))))
            )
            return DefaultRoomFactory(roomShapes)
        }
    }

    override fun createRoom(): Room {
        return roomShapes.shuffled().last().createRoom()
    }


}