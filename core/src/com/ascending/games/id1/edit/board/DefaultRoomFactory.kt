package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.Room
import com.ascending.games.id1.model.board.RoomElement
import com.ascending.games.id1.model.board.RoomShape
import com.ascending.games.id1.model.board.WallState
import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.geometry.Direction4

class DefaultRoomFactory(private val roomShapes : List<RoomShape>, val minDoors : Int, val maxDoors : Int) : IRoomFactory{

    companion object {
        const val DEFAULT_MIN_DOORS = 2
        const val DEFAULT_MAX_DOORS = 4

        fun createDefaultRoomFactory() : DefaultRoomFactory {
            val roomShapes = listOf<RoomShape>(
                    RoomShape(listOf(RoomElement(Coord2(0,0)), RoomElement(Coord2(0,-1)), RoomElement(Coord2(0,-2)))),
                    RoomShape(listOf(RoomElement(Coord2(0,0)), RoomElement(Coord2(0,-1)), RoomElement(Coord2(-1,-1)), RoomElement(Coord2(1,0)))),
                    RoomShape(listOf(RoomElement(Coord2(0,0)), RoomElement(Coord2(0,-1)), RoomElement(Coord2(0,-2)), RoomElement(Coord2(1,0)))),
                    RoomShape(listOf(RoomElement(Coord2(0,0)), RoomElement(Coord2(0,-1)), RoomElement(Coord2(1,0)), RoomElement(Coord2(1,-1)), RoomElement(Coord2(2,0))))
            )
            return DefaultRoomFactory(roomShapes, 2, 4)
        }
    }

    override fun createRoom(): Room {
        val room = roomShapes.shuffled().last().createRoom()
        val doors = (Math.random() * (maxDoors - minDoors)).toInt() + minDoors

        val closedWalls = room.roomElements.flatMap { it.getClosedWalls() }
        val wallsToOpen = closedWalls.shuffled().takeLast(doors)
        wallsToOpen.forEach { it.wallState = WallState.DOOR }

        return room
    }


}