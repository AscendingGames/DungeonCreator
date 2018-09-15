package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.*
import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.geometry.Direction4

class DefaultRoomFactory(private val roomShapes : List<RoomShape>, val factoryConfig: DefaultRoomFactoryConfig) : IRoomFactory{

    companion object {
        val SHAPE_LINE = RoomShape(listOf(Coord2(1,0), Coord2(0,0), Coord2(-1,0)))
        val SHAPE_L = RoomShape(listOf(Coord2(1,0), Coord2(0,0), Coord2(-1,0), Coord2(1,-1)))
        val SHAPE_Z = RoomShape(listOf(Coord2(0,0), Coord2(0,-1), Coord2(-1,-1), Coord2(1,0)))
        val SHAPE_HALF_CROSS = RoomShape(listOf(Coord2(0,0), Coord2(0,-1), Coord2(-1,0), Coord2(1,0)))
        val SHAPE_THUMB = RoomShape(listOf(Coord2(1,0), Coord2(0,0), Coord2(-1,0), Coord2(1,-1), Coord2(0,-1)))

        fun createDefaultRoomFactory() : DefaultRoomFactory {
            val roomShapes = listOf<RoomShape>(
                    SHAPE_LINE, SHAPE_L, SHAPE_Z, SHAPE_HALF_CROSS, SHAPE_THUMB
            )
            return DefaultRoomFactory(roomShapes, DefaultRoomFactoryConfig.createDefaultRoomFactoryConfig())
        }
    }

    private fun getNumberDoors() : Int {
        return factoryConfig.numberDoors.shuffled().last()
    }

    private fun getNumberMonsters() : Int {
        return factoryConfig.numberMonsters.shuffled().last()
    }

    private fun hasCrystal() : Boolean {
        return Math.random() <= factoryConfig.probHealingCrystal
    }

    override fun createRoom(): Room {
        val room = roomShapes.shuffled().last().createRoom()

        val numberDoors = getNumberDoors()
        val closedWalls = room.roomElements.flatMap { it.closedWalls }
        val wallsToOpen = closedWalls.shuffled().takeLast(numberDoors)
        wallsToOpen.forEach { it.wallState = WallState.DOOR }

        val numberMonsters  = getNumberMonsters()
        val shuffledElements = room.roomElements.shuffled()
        shuffledElements.take(numberMonsters).forEach { Monster(it) }
        val remainingElements = shuffledElements.drop(numberMonsters)

        if (remainingElements.isNotEmpty()  && hasCrystal()) {
            Crystal(Crystal.Type.HEALING, remainingElements[0])
        }

        return room
    }


}