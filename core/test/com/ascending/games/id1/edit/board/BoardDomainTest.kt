package com.ascending.games.id1.edit.board

import com.ascending.games.id1.edit.board.action.room.DropAction
import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.Wall
import com.ascending.games.id1.model.board.WallState
import com.ascending.games.lib.model.geometry.Direction4
import com.badlogic.gdx.math.Vector2
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.*
import org.junit.Test

class BoardDomainTest {

    private val board = Board(3,3)
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
        assertTrue("Room has reached the bottom", board.hasRoomFallen(board.rooms[0]))
        assertFalse("Hero has not yet spawned", boardDomain.hero.spawned)
        boardDomain.update(1f)
        assertEquals("Next room has been created", 2, board.rooms.size)
        assertTrue("Hero has spawned", boardDomain.hero.spawned)

        boardDomain.execute(DropAction())
        boardDomain.update(1f)

        assertEquals("Hero has moved to top room element", boardDomain.board.rooms[1].roomElements[0], boardDomain.hero.roomElement)
    }
}