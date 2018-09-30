package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.data.ObservableList
import com.ascending.games.lib.model.geometry.Coord2
import com.badlogic.gdx.math.Vector2
import org.junit.Assert.*
import org.junit.Test

class BoardTest {

    private val board = Board(3, 10)

    @Test
    fun roomsHaveFallen() {
        assertTrue("Initially no rooms are falling" , board.haveRoomsFallen())
        board.rooms += Room(ObservableList(mutableListOf(RoomElement(Coord2.ZERO))), Vector2(0f, 9f))
        assertFalse("Room is currently falling", board.haveRoomsFallen())
        board.rooms[0].position.y = 5f
        assertFalse("Room is still falling", board.haveRoomsFallen())
        board.rooms[0].position.y = 0f
        assertTrue("Room has again fallen", board.haveRoomsFallen())
        board.rooms += Room(ObservableList(mutableListOf(RoomElement(Coord2.ZERO))), Vector2(0f, 9f))
        assertFalse("Room is currently falling", board.haveRoomsFallen())
        board.rooms[1].position.y = 1f
        assertTrue("Room has again fallen", board.haveRoomsFallen())
    }

    @Test
    fun getRoomAt() {
        val room1 = Room(ObservableList(mutableListOf(RoomElement(Coord2.ZERO))))
        val room2 = Room(ObservableList(mutableListOf(RoomElement(Coord2.ZERO), RoomElement(Coord2(1,0)))), Vector2(1f, 0f))
        board.rooms.add(room1)
        board.rooms.add(room2)
        assertEquals(room1, board.getRoomAt(Coord2(0, 0)))
        assertEquals(room2, board.getRoomAt(Coord2(1, 0)))
        assertEquals(room2, board.getRoomAt(Coord2(2, 0)))
        assertNull(board.getRoomAt(Coord2(3, 0)))
    }
}