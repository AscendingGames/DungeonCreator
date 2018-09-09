package com.ascending.games.id1.edit.board.action

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.edit.board.MockRoomFactory
import com.ascending.games.id1.model.board.Board
import com.ascending.games.lib.model.geometry.Coord2
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class DropActionTest {

    @Test
    fun testExecute() {
        val boardDomain = BoardDomain(Board(3,3), MockRoomFactory())
        val dropAction = DropAction()

        assertNull(boardDomain.board.getRoomAt(Coord2(1, 0)))
        boardDomain.execute(dropAction)
        assertEquals(boardDomain.currentRoom, boardDomain.board.getRoomAt(Coord2(1, 0)))

        boardDomain.nextRoom()

        assertNull(boardDomain.board.getRoomAt(Coord2(1, 1)))
        boardDomain.execute(dropAction)
        assertEquals(boardDomain.currentRoom, boardDomain.board.getRoomAt(Coord2(1, 1)))
    }
}