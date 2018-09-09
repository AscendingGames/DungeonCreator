package com.ascending.games.id1.view

import com.ascending.games.id1.DungeonCreatorGame
import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.edit.board.IRoomFactory
import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.Room
import com.ascending.games.id1.model.board.RoomElement
import com.ascending.games.lib.model.geometry.Coord2
import com.badlogic.gdx.Screen
import com.badlogic.gdx.math.Vector2

class DungeonScreen(private val game : DungeonCreatorGame) : Screen{

    private val board = Board(10, 20)
    private val boardDomain = BoardDomain(board, object : IRoomFactory {
        override fun createRoom(): Room {
            return Room(listOf(RoomElement(Coord2(0, 0))), Vector2(0f, 0f))
        }
    })
    private val boardView = BoardView(board)

    override fun dispose() {

    }

    override fun resize(width: Int, height: Int) {

    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun render(delta: Float) {
        boardDomain.update(delta)
    }

    override fun show() {
        System.out.println("Showing Dungeon Screen")
        game.sceneManager.views.add(boardView)
    }

    override fun hide() {
        System.out.println("Hiding Dungeon Screen")
        game.sceneManager.views.remove(boardView)
    }
}