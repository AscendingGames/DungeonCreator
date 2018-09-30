package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.*
import com.ascending.games.engine.model.geometry.Coord2
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Test

class DefaultRoomFactoryTest {

    val testRoomShape = RoomShape(listOf(Coord2(0,0)))

    @Test
    fun createRoom() {
        val testRoomFactoryConfig = DefaultRoomFactoryConfig(listOf(testRoomShape))
        val defaultRoomFactory = DefaultRoomFactory(testRoomFactoryConfig, 1)
        assertEquals(0, defaultRoomFactory.numCreatedRooms)
        assertEquals(1, defaultRoomFactory.level)
        val room = defaultRoomFactory.createRoom()
        assertEquals(1, defaultRoomFactory.numCreatedRooms)
        assertEquals(1, room.roomElements.size)
        assertThat(room.allRoomClearables, not(hasItem(isA(Crystal::class.java))))
    }

    @Test
    fun createRoomWithMonster() {
        val testRoomFactoryConfig = DefaultRoomFactoryConfig(listOf(testRoomShape), numberMonsters = 1..1)
        val defaultRoomFactory = DefaultRoomFactory(testRoomFactoryConfig, 1)
        val room = defaultRoomFactory.createRoom()
        assertThat(room.allRoomClearables, hasItem(isA(Monster::class.java)))
    }

    @Test
    fun createRoomWithDoors() {
        val testRoomFactoryConfig = DefaultRoomFactoryConfig(listOf(testRoomShape), numberDoors = 1..1)
        val defaultRoomFactory = DefaultRoomFactory(testRoomFactoryConfig, 1)
        val room = defaultRoomFactory.createRoom()
        assertEquals(room.roomElements[0].closedWalls.size, 3)
    }

    @Test
    fun createRoomWithCrystal() {
        val testRoomFactoryConfig = DefaultRoomFactoryConfig(listOf(testRoomShape), probHealingCrystal = 1f)
        val defaultRoomFactory = DefaultRoomFactory(testRoomFactoryConfig, 1)
        val room = defaultRoomFactory.createRoom()
        assertThat(room.allRoomClearables, hasItem(isA(Crystal::class.java)))
    }

    @Test
    fun createRoomWithStairsDown() {
        val testRoomFactoryConfig = DefaultRoomFactoryConfig(listOf(testRoomShape), probStairsDown = 1f, minRoomsTillStairsDown = 1)
        val defaultRoomFactory = DefaultRoomFactory(testRoomFactoryConfig, 1)
        val room0 = defaultRoomFactory.createRoom()
        assertTrue(room0.allRoomClearables.isEmpty())
        val room1 = defaultRoomFactory.createRoom()
        assertThat(room1.allRoomClearables, hasItem(isA(StairsDown::class.java)))
    }

    @Test
    fun createRoomWithType() {
        val testRoomFactoryConfig = DefaultRoomFactoryConfig(listOf(testRoomShape), roomTypeMinLevels = mutableMapOf(RoomType.DANGER_HIGH to 1), roomTypePriorities = mutableMapOf(RoomType.DANGER_HIGH to 1))
        val defaultRoomFactory = DefaultRoomFactory(testRoomFactoryConfig, 1)
        val room = defaultRoomFactory.createRoom()
        assertEquals(RoomType.DANGER_HIGH, room.type)
    }
}