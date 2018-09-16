package com.ascending.games.id1.edit.board.action.room

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.edit.board.MockRoomFactory
import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.RoomElement
import com.ascending.games.id1.model.world.Player
import com.ascending.games.lib.model.geometry.Coord2
import org.junit.Test

import org.junit.Assert.*

class RotateActionTest {

    @Test
    fun execute() {
        val boardDomain = BoardDomain(Board(3,3), Player(), MockRoomFactory())
        boardDomain.currentRoom.position.y = 0f
        boardDomain.currentRoom.roomElements += RoomElement(Coord2(1,0))
        boardDomain.currentRoom.roomElements[1].room = boardDomain.currentRoom
        val rotateAction = RotateAction()

        assertNull(boardDomain.board.getRoomAt(Coord2(0, 0)))
        boardDomain.execute(rotateAction)
        assertEquals(boardDomain.currentRoom, boardDomain.board.getRoomAt(Coord2(0, 0)))
    }
}