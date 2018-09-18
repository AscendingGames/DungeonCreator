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

class OverworldView(worldScreen: WorldScreen, uiStage : Stage) : ALocationView(worldScreen, uiStage, OVERWORLD_TEXT) {
    companion object {
        const val OVERWORLD_TEXT =
                "As you gaze downwards, you see the mortal world\n"+
                "unfolding before you..."
    }

    private val dungeonButton = TextButton("Dungeon", skin, "overworld")
    private val shrineButton = TextButton("Shrine", skin, "overworld")

    init {
        locationTable.add(dungeonButton)
        dungeonButton.listeners.add(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if (event != null) {
                    worldScreen.setLocation(Location.DUNGEON)
                    event.handle()
                }
            }
        })

        locationTable.row().pad(100f)
        locationTable.add(shrineButton)
        shrineButton.listeners.add(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if (event != null) {
                    worldScreen.setLocation(Location.SHRINE)
                    event.handle()
                }
            }
        })
    }
}