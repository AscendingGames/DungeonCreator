package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.Crystal
import com.ascending.games.id1.model.board.Monster
import com.ascending.games.id1.model.board.RoomShape
import com.ascending.games.id1.model.board.StairsDown
import com.ascending.games.lib.model.geometry.Coord2
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Test

import org.junit.Assert.*

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
        assertThat(room.allRoomContents, not(hasItem(isA(Crystal::class.java))))
    }

    @Test
    fun createRoomWithMonster() {
        val testRoomFactoryConfig = DefaultRoomFactoryConfig(listOf(testRoomShape), numberMonsters = 1..1)
        val defaultRoomFactory = DefaultRoomFactory(testRoomFactoryConfig, 1)
        val room = defaultRoomFactory.createRoom()
        assertThat(room.allRoomContents, hasItem(isA(Monster::class.java)))
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
        assertThat(room.allRoomContents, hasItem(isA(Crystal::class.java)))
    }

    @Test
    fun createRoomWithStairsDown() {
        val testRoomFactoryConfig = DefaultRoomFactoryConfig(listOf(testRoomShape), probStairsDown = 1f, minRoomsTillStairsDown = 1)
        val defaultRoomFactory = DefaultRoomFactory(testRoomFactoryConfig, 1)
        val room0 = defaultRoomFactory.createRoom()
        assertTrue(room0.allRoomContents.isEmpty())
        val room1 = defaultRoomFactory.createRoom()
        assertThat(room1.allRoomContents, hasItem(isA(StairsDown::class.java)))
    }
}