package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.world.Location
import com.ascending.games.lib.view.IVisible
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener

class ShrineView(worldScreen: WorldScreen, uiStage : Stage) : ALocationView(worldScreen, uiStage, SHRINE_TEXT) {
    companion object {
        const val SHRINE_TEXT =
                "This is your shrine, nothing but a humongous stone\n"+
                "with random inscriptions scribbled on it.\n"+
                "Whoever did this had not expected any results.\n" +
                "A creation of despair -- a creation of wishes..."
    }

    private val buttonBack : TextButton = TextButton("Back", skin)

    init {
        locationTable.add(buttonBack)
        buttonBack.listeners.add(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if (event != null) {
                    worldScreen.setLocation(Location.OVERWORLD)
                    event.handle()
                }
            }
        })
    }
}