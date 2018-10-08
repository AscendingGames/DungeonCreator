package com.ascending.games.id1.edit.board.action.room

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.edit.board.MockRoomFactory
import com.ascending.games.id1.edit.board.action.room.DropAction
import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.world.Player
import com.ascending.games.engine.model.geometry.Coord2
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class DropActionTest {

    @Test
    fun execute() {
        val boardDomain = BoardDomain(Board(3,3), Player(), 1, MockRoomFactory())
        val dropAction = DropAction()

        assertNull(boardDomain.board.getRoomAt(Coord2(1, 0)))
        boardDomain.execute(dropAction)
        assertEquals(boardDomain.currentRoom, boardDomain.board.getRoomAt(Coord2(1, 1)))
        boardDomain.execute(dropAction)
        assertEquals(boardDomain.currentRoom, boardDomain.board.getRoomAt(Coord2(1, 0)))

        boardDomain.nextRoom()

        assertNull(boardDomain.board.getRoomAt(Coord2(1, 1)))
        boardDomain.execute(dropAction)
        assertEquals(boardDomain.currentRoom, boardDomain.board.getRoomAt(Coord2(1, 1)))
        boardDomain.execute(dropAction)
        assertEquals(boardDomain.currentRoom, boardDomain.board.getRoomAt(Coord2(1, 1)))
    }
}