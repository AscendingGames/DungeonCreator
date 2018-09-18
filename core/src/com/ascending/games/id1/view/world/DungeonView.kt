package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.world.Location
import com.ascending.games.lib.view.IVisible
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.Align

class DungeonView(private val worldScreen: WorldScreen, private val uiStage : Stage) : IVisible {
    companion object {
        const val ENTRANCE_TEXT =
                "Laid out before you stands a labyrinth so deep even a being such as you cannot grasp\n" +
                "its full depth. An ambitious group of your followers has gathered here to challenge\n" +
                "these depths for the vast amount of riches hidden deep inside..."
    }

    private val player = worldScreen.game.player
    private val skin = worldScreen.game.skin

    private val dungeonTable = Table()
    private val entranceLabel = Label(ENTRANCE_TEXT, skin)
    private val buttonCurrentLevel = TextButton("", skin)
    private val buttonNextLevel : TextButton = TextButton("", skin)
    private val buttonBack : TextButton = TextButton("Back", skin)

    init {
        dungeonTable.setFillParent(true)
        dungeonTable.align(Align.top)
        dungeonTable.pad(100f)

        entranceLabel.setWrap(true)
        entranceLabel.setAlignment(Align.center or Align.top)

        dungeonTable.add(entranceLabel)
        dungeonTable.row().pad(100f)
        dungeonTable.add(buttonCurrentLevel)
        dungeonTable.row().pad(100f)
        dungeonTable.add(buttonNextLevel)
        dungeonTable.row().pad(100f)
        dungeonTable.add(buttonBack)

        buttonCurrentLevel.listeners.add(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if (event != null) {
                    worldScreen.startLevel(player.knownDepths)
                    event.handle()
                }
            }
        })

        buttonNextLevel.listeners.add(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if (event != null) {
                    worldScreen.startLevel(player.newDepths)
                    event.handle()
                }
            }
        })

        buttonBack.listeners.add(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if (event != null) {
                    worldScreen.setLocation(Location.OVERWORLD)
                }
            }
        })
    }

    override fun show() {
        val newDepths  = player.newDepths
        val knownDepths = player.knownDepths

        buttonCurrentLevel.label.setText("Enter known depths (Level $knownDepths)")
        buttonNextLevel.label.setText("Explore new depths (Level $newDepths)")

        if (newDepths == 1) buttonNextLevel.isDisabled = true

        uiStage.addActor(dungeonTable)
    }

    override fun hide() {
        dungeonTable.remove()
    }
}