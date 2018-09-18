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

class OverworldView(private val worldScreen: WorldScreen, private val uiStage : Stage) : IVisible {
    companion object {
        const val OVERWORLD_TEXT =
                "As you gaze downwards, you see the mortal world\n"+
                "unfolding before you..."
    }

    private val skin = worldScreen.game.skin

    private val overworldTable = Table()
    private val overworldLabel = Label(OVERWORLD_TEXT, skin)
    private val dungeonButton = TextButton("Dungeon", skin, "overworld")

    init {
        overworldTable.setFillParent(true)
        overworldTable.align(Align.top)
        overworldTable.pad(100f)

        overworldTable.add(overworldLabel)
        overworldLabel.setWrap(true)
        overworldLabel.setAlignment(Align.center or Align.top)
        overworldTable.row().pad(100f)

        overworldTable.add(dungeonButton)
        dungeonButton.listeners.add(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if (event != null) {
                    worldScreen.setLocation(Location.DUNGEON)
                    event.handle()
                }
            }
        })
    }

    override fun show() {
        uiStage.addActor(overworldTable)
    }

    override fun hide() {
        overworldTable.remove()
    }
}