package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.Room
import com.ascending.games.id1.model.board.RoomElement
import com.ascending.games.lib.model.geometry.Coord2
import com.badlogic.gdx.math.Vector2
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.*
import org.junit.Test

class BoardDomainTest {

    private val board = Board(2,2)
    private val boardDomain = BoardDomain(board, MockRoomFactory())

    @Test
    fun testSpawnRoom() {
        val oldWaitingRooms = boardDomain.waitingRooms
        boardDomain.nextRoom()

        assertEquals("New current room is the first waiting room", oldWaitingRooms[0], boardDomain.currentRoom)
        assertEquals("New room has been positioned in the middle top of the board", Vector2(1f, 2f), boardDomain.currentRoom.position)
        assertThat("Board contains newly spawned room", board.rooms, hasItem(boardDomain.currentRoom))
    }

    @Test
    fun testUpdate() {
        assertFalse("Room has not moved to the bottom yet", board.haveRoomsFallen())
        boardDomain.update(1f)
        assertFalse("Room has not moved to the bottom yet", board.haveRoomsFallen())
        boardDomain.update(1f)
        assertTrue("Room has reached the bottom", board.hasRoomFallen(board.rooms.get(0)))
        boardDomain.update(1f)
        assertEquals("Next room has been created", 2, board.rooms.size)
    }
}