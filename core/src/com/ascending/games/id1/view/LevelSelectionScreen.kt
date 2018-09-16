package com.ascending.games.id1.view

import com.ascending.games.id1.DungeonCreatorGame
import com.badlogic.gdx.Screen
import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table
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


class LevelSelectionScreen(private val game : DungeonCreatorGame) : Screen {
    private val stage = Stage()
    private val table = Table()
    private val skin = Skin()
    private var nameLabel : Label
    private var buttonCurrentLevel : Button
    private var buttonNextLevel : Button

    companion object {
        const val ENTRANCE_TEXT =   "Laid out before you stands a labyrinth so deep even a being such as you cannot grasp its full depth.\n" +
                                                "An ambitious group of your followers has gathered here to challenge these depths for the vast amount\n" +
                                                "of riches hidden deep inside..."
    }

    init {
        Gdx.input.inputProcessor = stage
        table.setFillParent(true)
        table.debug()
        table.align(Align.top)
        stage.addActor(table)

        // Generate a 1x1 white texture and store it in the skin named "white".
        val pixmap = Pixmap(1, 1, Pixmap.Format.RGBA8888)
        pixmap.setColor(Color.WHITE)
        pixmap.fill()
        skin.add("white", Texture(pixmap))

        // Store the default libgdx font under the name "default".
        skin.add("default", BitmapFont())
        skin.add("default", Color.WHITE)

        val labelStyle = Label.LabelStyle()
        labelStyle.font = skin.getFont("default")
        labelStyle.fontColor = skin.getColor("default")
        skin.add("default", labelStyle)

        val buttonStyle = TextButtonStyle()
        buttonStyle.down = skin.newDrawable("white", Color.RED)
        buttonStyle.up = skin.newDrawable("white", Color.WHITE)
        buttonStyle.disabled = skin.newDrawable("white", Color.GRAY)
        buttonStyle.font = skin.getFont("default")
        skin.add("default", buttonStyle)

        nameLabel = Label(ENTRANCE_TEXT, skin)
        nameLabel.setWrap(true)
        nameLabel.setAlignment(Align.center or Align.top)
        table.add(nameLabel)
        table.row().pad(100f)

        buttonCurrentLevel = TextButton("Enter known depths (Level 1)", skin)
        buttonCurrentLevel.listeners.add(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if (event != null) {
                    startLevel(1)
                    event.handle()
                }
            }
        })
        table.add(buttonCurrentLevel)
        table.row()

        buttonNextLevel = TextButton("Explore new depths (Level 2)", skin)
        buttonNextLevel.isDisabled = true
        table.add(buttonNextLevel)

        table.pack()
    }

    private fun startLevel(level : Int) {
        game.screen = DungeonScreen(game, level)
        hide()
    }

    override fun hide() {

    }

    override fun show() {

    }

    override fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun dispose() {
        stage.dispose()
    }
}