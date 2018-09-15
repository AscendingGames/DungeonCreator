package com.ascending.games.id1.edit.board

import com.ascending.games.id1.edit.board.action.room.DropAction
import com.ascending.games.id1.model.board.*
import com.ascending.games.lib.model.geometry.Coord2
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
        assertFalse("Hero has not yet spawned", boardDomain.board.hero.spawned)
        boardDomain.update(1f)
        assertEquals("Next room has been created", 2, board.rooms.size)
        assertTrue("Hero has spawned", boardDomain.board.hero.spawned)

        boardDomain.execute(DropAction())
        boardDomain.update(1f)

        assertEquals("Hero has moved to top room element", boardDomain.board.rooms[1].roomElements[0], boardDomain.board.hero.roomElement)
    }

    @Test
    fun testClearRowIfFull() {
        boardDomain.board.rooms.clear()
        assertFalse(boardDomain.clearRowIfFull(0))
        board.rooms += Room(mutableListOf(RoomElement(Coord2.ZERO)))
        board.rooms[0].isVisited = true
        assertFalse(boardDomain.clearRowIfFull(0))
        board.rooms.add(Room(mutableListOf(RoomElement(Coord2.ZERO), RoomElement(Coord2(1,0))), Vector2(1f, 0f)))
        board.rooms[1].isVisited = true
        assertTrue(boardDomain.clearRowIfFull(0))
        assertTrue(boardDomain.board.rooms.isEmpty())
    }
}