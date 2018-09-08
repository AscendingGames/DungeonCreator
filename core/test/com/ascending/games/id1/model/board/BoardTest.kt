package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.geometry.Coord2
import com.badlogic.gdx.math.Vector2
import org.junit.Test

import org.junit.Assert.*

class BoardTest {

    private val board = Board(3, 10)

    @Test
    fun roomsHaveFallen() {
        assertTrue("Initially no rooms are falling" , board.haveRoomsFallen())
        board.rooms += Room(listOf(RoomElement(Coord2.ZERO)), Vector2(0f, 10f))
        assertFalse("Room is currently falling", board.haveRoomsFallen())
        board.rooms.get(0).position.y = 5f
        assertFalse("Room is still falling", board.haveRoomsFallen())
        board.rooms.get(0).position.y = 0f
        assertTrue("Room has again fallen", board.haveRoomsFallen())
        board.rooms += Room(listOf(RoomElement(Coord2.ZERO)), Vector2(0f, 10f))
        assertFalse("Room is currently falling", board.haveRoomsFallen())
        board.rooms.get(1).position.y = 1f
        assertTrue("Room has again fallen", board.haveRoomsFallen())
    }

    @Test
    fun testGetRoomAt() {
        val room1 = Room(listOf(RoomElement(Coord2.ZERO)))
        val room2 = Room(listOf(RoomElement(Coord2.ZERO), RoomElement(Coord2(1,0))), Vector2(1f, 0f))
        board.rooms = board.rooms + room1 + room2
        assertEquals(room1, board.getRoomAt(Coord2(0, 0)))
        assertEquals(room2, board.getRoomAt(Coord2(1, 0)))
        assertEquals(room2, board.getRoomAt(Coord2(2, 0)))
        assertNull(board.getRoomAt(Coord2(3, 0)))
    }

    @Test
    fun testClearRowIfFull() {
        assertFalse(board.clearRowIfFull(0))
        board.rooms += Room(listOf(RoomElement(Coord2.ZERO)))
        assertFalse(board.clearRowIfFull(0))
        board.rooms += Room(listOf(RoomElement(Coord2.ZERO), RoomElement(Coord2(1,0))), Vector2(1f, 0f))
        assertTrue(board.clearRowIfFull(0))
        assertTrue(board.rooms.isEmpty())
    }
}