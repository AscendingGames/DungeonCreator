package com.ascending.games.id1.view.title

import com.ascending.games.id1.DungeonCreatorGame
import com.ascending.games.id1.view.world.WorldScreen
import com.ascending.games.lib.view.ui.ChangeListenerService
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.Align

class TitleScreen(val game : DungeonCreatorGame) : Screen {
    private val uiStage = Stage()
    private val titleTable = Table()
    private val listenerService  = ChangeListenerService()
    private val buttonStart : TextButton = TextButton("Start", game.skin)
    private val buttonContinue : TextButton = TextButton("Continue", game.skin)


    init {
        titleTable.setFillParent(true)
        titleTable.align(Align.top)
        titleTable.pad(100f)
        titleTable.add(buttonStart)
        titleTable.row().pad(100f)
        titleTable.add(buttonContinue)
        titleTable.row().pad(100f)

        buttonStart.listeners.add(listenerService.createChangeListener { startGame() })
        buttonContinue.listeners.add(listenerService.createChangeListener { startGame() })
    }

    private fun startGame() {
        game.screen = WorldScreen(game)
    }

    override fun hide() {
        titleTable.remove()
    }

    override fun show() {
        Gdx.input.inputProcessor = uiStage
        uiStage.addActor(titleTable)
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