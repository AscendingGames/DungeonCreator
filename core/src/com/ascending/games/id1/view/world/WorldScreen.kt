package com.ascending.games.id1.view.world

import com.ascending.games.id1.DungeonCreatorGame
import com.ascending.games.id1.view.board.BoardScreen
import com.ascending.games.id1.view.mechanics.StatsView
import com.ascending.games.lib.model.data.ObservableMap
import com.badlogic.gdx.Screen
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.scenes.scene2d.*
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.Align


class WorldScreen(private val game : DungeonCreatorGame) : Screen {
    private val skin = game.skin
    private val uiStage = Stage()
    private val dungeonTable = Table()
    private val statsView = StatsView(game.player.stats, uiStage, skin)

    private var nameLabel : Label
    private var buttonCurrentLevel : Button
    private var buttonNextLevel : Button

    companion object {
        const val ENTRANCE_TEXT =   "Laid out before you stands a labyrinth so deep even a being such as you cannot grasp its full depth.\n" +
                                                "An ambitious group of your followers has gathered here to challenge these depths for the vast amount\n" +
                                                "of riches hidden deep inside..."
    }

    init {
        dungeonTable.setFillParent(true)
        dungeonTable.align(Align.top)
        dungeonTable.pad(100f)
        uiStage.addActor(dungeonTable)

        nameLabel = Label(ENTRANCE_TEXT, skin)
        nameLabel.setWrap(true)
        nameLabel.setAlignment(Align.center or Align.top)
        dungeonTable.add(nameLabel)
        dungeonTable.row().pad(100f)

        buttonCurrentLevel = TextButton("Enter known depths (Level 1)", skin)
        buttonCurrentLevel.listeners.add(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if (event != null) {
                    startLevel(1)
                    event.handle()
                }
            }
        })
        dungeonTable.add(buttonCurrentLevel)
        dungeonTable.row()

        buttonNextLevel = TextButton("Explore new depths (Level 2)", skin)
        buttonNextLevel.isDisabled = true
        dungeonTable.add(buttonNextLevel)
    }

    private fun startLevel(level : Int) {
        game.screen = BoardScreen(game, level)
        hide()
    }

    override fun hide() {

    }

    override fun show() {
        Gdx.input.inputProcessor = uiStage
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