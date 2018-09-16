package com.ascending.games.id1.view.board

import com.ascending.games.id1.DungeonCreatorGame
import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.edit.board.DefaultRoomFactory
import com.ascending.games.id1.edit.board.action.room.GestureActionProvider
import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.world.PlayerService
import com.ascending.games.id1.view.mechanics.StatsView
import com.ascending.games.id1.view.world.WorldScreen
import com.ascending.games.lib.model.data.ObservableMap
import com.ascending.games.lib.model.geometry.Coord2
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table

class BoardScreen(private val game : DungeonCreatorGame, level : Int) : Screen {
    private val skin = game.skin
    private val uiStage = Stage()

    companion object {
        val BOARD_SIZE = Coord2(10, 20)
        const val THRESHOLD = 1f
    }

    private val board = Board(BOARD_SIZE.x, BOARD_SIZE.y)
    private val boardDomain = BoardDomain(board, game.player, DefaultRoomFactory.createDefaultRoomFactory(level))
    private val boardView = BoardView(board)
    private val gestureActionProvider = GestureActionProvider()
    private var currentRoomView = ProjectedRoomView(boardDomain.projectedRoom, boardView.shapeRenderer)

    private val statsView = StatsView(board.hero.stats, uiStage, skin)

    init {
        Gdx.input.inputProcessor = uiStage
        game.sceneManager.views.add(currentRoomView)

        boardDomain.onProjectedRoomChanged += { ->
            game.sceneManager.views.remove(currentRoomView)
            currentRoomView = ProjectedRoomView(boardDomain.projectedRoom, boardView.shapeRenderer)
            game.sceneManager.views.add(currentRoomView)
        }

        boardDomain.onBoardFinished += {
            game.screen = WorldScreen(game)
            hide()
        }
    }

    override fun dispose() {
        boardView.dispose()
        uiStage.dispose()
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun resume() {

    }

    override fun pause() {

    }

    override fun render(delta: Float) {
        uiStage.act(delta)
        uiStage.draw()

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
        game.sceneManager.views.remove(currentRoomView)
    }
}