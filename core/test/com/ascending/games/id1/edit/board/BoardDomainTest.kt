package com.ascending.games.id1.edit.board

import com.ascending.games.id1.edit.board.action.room.DropAction
import com.ascending.games.id1.model.board.*
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.id1.model.world.PlayerService
import com.ascending.games.engine.model.data.ObservableList
import com.ascending.games.engine.model.geometry.Coord2
import com.badlogic.gdx.math.Vector2
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.*
import org.junit.Test

class BoardDomainTest {

    private val board = Board(3,3)
    private val boardDomain = BoardDomain(board, PlayerService().createInitialPlayer(), 1, MockRoomFactory())

    @Test
    fun spawnRoom() {
        val firstWaitingRoom = boardDomain.roomPool.waitingRooms[0]
        boardDomain.nextRoom()

        assertEquals("New current room is the first waiting room", firstWaitingRoom, boardDomain.currentRoom)
        assertEquals("New room has been positioned in the middle top of the board", Vector2(1f, 2f), boardDomain.currentRoom.position)
        assertThat("Board contains newly spawned room", board.rooms, hasItem(boardDomain.currentRoom))
    }

    @Test
    fun update() {
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
        boardDomain.update(0.25f)
        assertEquals("Hero has not yet reached the top room element", boardDomain.board.rooms[0].roomElements[0], boardDomain.board.hero.roomElement)
        boardDomain.update(0.25f)
        assertEquals("Hero has moved to top room element", boardDomain.board.rooms[1].roomElements[0], boardDomain.board.hero.roomElement)

        Monster(1).spawn(boardDomain.board.rooms[1].roomElements[0])
        boardDomain.update(1f)
        assertTrue("Hero has cleared room", boardDomain.board.rooms[1].roomElements[0].clearables.isEmpty())
        assertTrue("Hero has been hurt", boardDomain.board.hero.stats[StatType.CURRENT_HP.name]!! < boardDomain.board.hero.stats[StatType.MAX_HP.name]!!)

        Crystal(Crystal.Type.HEALING).spawn(boardDomain.board.rooms[1].roomElements[0])
        boardDomain.update(1f)
        assertTrue("Hero has been healed", boardDomain.board.hero.stats[StatType.CURRENT_HP.name]!! == boardDomain.board.hero.stats[StatType.MAX_HP.name]!!)
    }

    @Test
    fun clearBoard() {
        board.hero.stats[StatType.LEVEL.name] = 2f
        boardDomain.onBoardFinished += { clear ->
            assertTrue(clear)
            assertEquals(2f, boardDomain.player.stats[StatType.LEVEL.name])
        }
        boardDomain.clearBoard()
    }

    @Test
    fun projectedRoom() {
        assertEquals(Vector2(1f,0f), boardDomain.projectedRoom.position)
        boardDomain.execute(DropAction())
        boardDomain.update(0f)
        assertEquals(Vector2(1f,1f), boardDomain.projectedRoom.position)
    }

    @Test
    fun clearRowIfFull() {
        boardDomain.board.rooms.clear()
        assertFalse(boardDomain.clearRowIfFull(0))
        board.rooms += Room(ObservableList(mutableListOf(RoomElement(Coord2.ZERO))))
        board.rooms[0].isVisited = true
        assertFalse(boardDomain.clearRowIfFull(0))
        board.rooms.add(Room(ObservableList(mutableListOf(RoomElement(Coord2.ZERO), RoomElement(Coord2(1,0)))), Vector2(1f, 0f)))
        board.rooms[1].isVisited = true
        assertTrue(boardDomain.clearRowIfFull(0))
        assertTrue(boardDomain.board.rooms.isEmpty())
    }
}