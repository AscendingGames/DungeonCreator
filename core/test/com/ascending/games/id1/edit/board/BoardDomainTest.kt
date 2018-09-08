package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.Room
import com.ascending.games.id1.model.board.RoomElement
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

class BoardDomainTest {

    private val board = Board(0,0)
    private val boardDomain = BoardDomain(board, object : IRoomFactory {
        override fun createRoom(): Room {
            return Room(0.0,0.0,emptyList<RoomElement>())
        }
    })

    @Test
    fun testSpawnRoom() {
        val oldWaitingRooms = boardDomain.waitingRooms
        boardDomain.nextRoom()

        assertEquals("New current room is the first waiting room", oldWaitingRooms[0], boardDomain.currentRoom)
        assertThat("Board contains newly spawned room", board.rooms, hasItem(boardDomain.currentRoom))
        assertThat("New waiting room has been inserted into the waiting list", oldWaitingRooms, not(hasItem(boardDomain.waitingRooms.last())))
    }
}