package com.ascending.games.id1.view.world

import com.ascending.games.id1.DungeonCreatorGame
import com.ascending.games.id1.model.world.Location
import com.ascending.games.id1.view.board.BoardScreen
import com.ascending.games.id1.view.mechanics.StatsView
import com.badlogic.gdx.Screen
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.*


class WorldScreen(val game : DungeonCreatorGame) : Screen {
    private val uiStage = Stage()

    private val statsView = StatsView(game.player.stats, uiStage, game.skin)
    private val locationViews = Location.values().associate {
                it to when (it) {
                    Location.DUNGEON -> DungeonView(this, uiStage)
                    Location.OVERWORLD -> OverworldView(this, uiStage)
                    Location.SHRINE -> ShrineView(this, uiStage)
                    Location.CITY -> CityView(this, uiStage)
                }
            }


    private var currentLocation = Location.DUNGEON

    fun startLevel(level : Int) {
        game.screen = BoardScreen(game, level)
    }

    fun setLocation(location : Location) {
        locationViews[currentLocation]!!.hide()
        currentLocation = location
        locationViews[currentLocation]!!.show()
    }

    override fun hide() {
        locationViews[currentLocation]!!.hide()
    }

    override fun show() {
        Gdx.input.inputProcessor = uiStage
        locationViews[currentLocation]!!.show()
    }

    override fun render(delta: Float) {
        uiStage.act(delta)
        uiStage.draw()
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {
        uiStage.viewport.update(width, height, true)
    }

    override fun dispose() {
        uiStage.dispose()
    }
}