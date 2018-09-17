package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.*

class DefaultRoomFactory(private val factoryConfig: DefaultRoomFactoryConfig, val level : Int) : IRoomFactory{

    companion object {
        fun createDefaultRoomFactory(level : Int) : DefaultRoomFactory {
            return DefaultRoomFactory(DefaultRoomFactoryConfig.createDefaultRoomFactoryConfig(), level)
        }
    }

    var numCreatedRooms = 0

    private fun getNumberDoors() : Int {
        return factoryConfig.numberDoors.shuffled().last()
    }

    private fun getNumberMonsters() : Int {
        return factoryConfig.numberMonsters.shuffled().last()
    }

    private fun hasCrystal() : Boolean {
        return Math.random() <= factoryConfig.probHealingCrystal
    }

    private fun hasStairsDown() : Boolean {
        return numCreatedRooms >= factoryConfig.minRoomsTillStairsDown && Math.random() <= factoryConfig.probStairsDown
    }

    override fun createRoom(): Room {
        val room = factoryConfig.roomShapes.shuffled().last().createRoom()

        val numberDoors = getNumberDoors()
        val closedWalls = room.roomElements.flatMap { it.closedWalls }
        val wallsToOpen = closedWalls.shuffled().takeLast(numberDoors)
        wallsToOpen.forEach { it.wallState = WallState.DOOR }

        val numberMonsters  = getNumberMonsters()
        val shuffledElements = room.roomElements.shuffled()
        shuffledElements.take(numberMonsters).forEach { Monster(level).spawn(it) }
        var remainingElements = shuffledElements.drop(numberMonsters)

        if (remainingElements.isNotEmpty() && hasCrystal()) {
            Crystal(Crystal.Type.HEALING, remainingElements[0])
            remainingElements = remainingElements.drop(0)
        }

        if (remainingElements.isNotEmpty() && hasStairsDown()) {
            StairsDown(remainingElements[0])
        }

        numCreatedRooms++

        return room
    }


}