package com.ascending.games.id1.edit.board.action

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.edit.board.MockRoomFactory
import com.ascending.games.id1.model.board.Board
import com.ascending.games.lib.model.geometry.Coord2
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class SlideActionTest {

    @Test
    fun testExecute() {
        val boardDomain = BoardDomain(Board(3,3), MockRoomFactory())
        val slideAction = SlideAction(-4)

        assertNull(boardDomain.board.getRoomAt(Coord2(0, 3)))
        boardDomain.execute(slideAction)
        assertEquals(boardDomain.currentRoom, boardDomain.board.getRoomAt(Coord2(0, 2)))
    }
}