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

        val SHAPE_LINE = RoomShape(listOf(Coord2(1,0), Coord2(0,0), Coord2(-1,0)))
        val SHAPE_L = RoomShape(listOf(Coord2(1,0), Coord2(0,0), Coord2(-1,0), Coord2(1,-1)))
        val SHAPE_Z = RoomShape(listOf(Coord2(0,0), Coord2(0,-1), Coord2(-1,-1), Coord2(1,0)))
        val SHAPE_HALF_CROSS = RoomShape(listOf(Coord2(0,0), Coord2(0,-1), Coord2(-1,0), Coord2(1,0)))
        val SHAPE_THUMB = RoomShape(listOf(Coord2(1,0), Coord2(0,0), Coord2(-1,0), Coord2(1,-1), Coord2(0,-1)))

        fun createDefaultRoomFactory() : DefaultRoomFactory {
            val roomShapes = listOf<RoomShape>(
                    SHAPE_LINE, SHAPE_L, SHAPE_Z, SHAPE_HALF_CROSS, SHAPE_THUMB
            )
            return DefaultRoomFactory(roomShapes, DEFAULT_MIN_DOORS, DEFAULT_MAX_DOORS)
        }
    }

    override fun createRoom(): Room {
        val room = roomShapes.shuffled().last().createRoom()
        val doors = (Math.random() * (maxDoors - minDoors)).toInt() + minDoors

        val closedWalls = room.roomElements.flatMap { it.closedWalls }
        val wallsToOpen = closedWalls.shuffled().takeLast(doors)
        wallsToOpen.forEach { it.wallState = WallState.DOOR }

        return room
    }


}