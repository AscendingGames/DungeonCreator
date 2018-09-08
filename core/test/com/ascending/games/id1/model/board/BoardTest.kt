package com.ascending.games.id1.model.board

import org.junit.Test

import org.junit.Assert.*

class BoardTest {

    val board = Board(3, 10)

    @Test
    fun roomsHaveFallen() {
        assertTrue("Initially no rooms are falling" , board.roomsHaveFallen())
        board.rooms += Room(0.0, 10.0,  listOf(RoomElement(0,0)))
        assertFalse("Room is currently falling", board.roomsHaveFallen())
        board.rooms.get(0).y = 5.0
        assertFalse("Room is still falling", board.roomsHaveFallen())
        board.rooms.get(0).y = 0.0
        assertTrue("Room has again fallen", board.roomsHaveFallen())
        board.rooms += Room(0.0, 10.0,  listOf(RoomElement(0,0)))
        assertFalse("Room is currently falling", board.roomsHaveFallen())
        board.rooms.get(1).y = 1.0
        assertTrue("Room has again fallen", board.roomsHaveFallen())
    }

    @Test
    fun testGetRoomAt() {
        val room1 = Room(0.0, 0.0,  listOf(RoomElement(0,0)))
        val room2 = Room(1.0, 0.0,  listOf(RoomElement(0,0), RoomElement(1,0)))
        board.rooms = board.rooms + room1 + room2
        assertEquals(room1, board.getRoomAt(0, 0))
        assertEquals(room2, board.getRoomAt(1, 0))
        assertEquals(room2, board.getRoomAt(2, 0))
        assertNull(board.getRoomAt(3, 0))
    }

    @Test
    fun testIsRowFull() {
        assertFalse(board.isRowFull(0))
        board.rooms += Room(0.0, 0.0,  listOf(RoomElement(0,0)))
        assertFalse(board.isRowFull(0))
        board.rooms += Room(1.0, 0.0,  listOf(RoomElement(0,0), RoomElement(1,0)))
        assertTrue(board.isRowFull(0))
    }
}