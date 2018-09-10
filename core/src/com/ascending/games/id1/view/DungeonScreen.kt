package com.ascending.games.id1.view

import com.ascending.games.id1.DungeonCreatorGame
import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.edit.board.DefaultRoomFactory
import com.ascending.games.id1.edit.board.action.room.GestureActionProvider
import com.ascending.games.id1.model.board.Board
import com.ascending.games.lib.model.geometry.Coord2
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.input.GestureDetector

class DungeonScreen(private val game : DungeonCreatorGame) : Screen{

    companion object {
        val BOARD_SIZE = Coord2(10, 20)
        val THRESHOLD = 1f
    }

    private val board = Board(BOARD_SIZE.x, BOARD_SIZE.y)
    private val boardDomain = BoardDomain(board, DefaultRoomFactory.createDefaultRoomFactory())
    private val boardView = BoardView(board)
    private val gestureActionProvider = GestureActionProvider()

    override fun dispose() {
        boardView.dispose()
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun render(delta: Float) {
        if (delta > THRESHOLD) return

        boardDomain.update(delta)
        gestureActionProvider.actionBuffer.forEach { boardDomain.execute(it) }
        gestureActionProvider.actionBuffer.clear()
    }

    override fun show() {
        System.out.println("Showing Dungeon Screen")
        game.sceneManager.views.add(boardView)
        Gdx.input.inputProcessor = GestureDetector(gestureActionProvider)
    }

    override fun hide() {
        System.out.println("Hiding Dungeon Screen")
        game.sceneManager.views.remove(boardView)
    }
}