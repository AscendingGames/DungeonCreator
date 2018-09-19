package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.world.Location
import com.ascending.games.lib.view.IVisible
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener

class CityView(worldScreen: WorldScreen, uiStage : Stage) : ALocationView(worldScreen, uiStage, CITY_TEXT) {
    companion object {
        const val CITY_TEXT =
                "You observe the old city residing in your domain.\n"+
                "The wooden buildings are run-down and damaged,\n"+
                "most repairs only performed in a sloppy manner.\n" +
                "Clearly this place is ridden by poverty..."
    }

    private val buttonBack : TextButton = TextButton("Back", skin)

    init {
        locationTable.add(buttonBack)
        buttonBack.listeners.add(createChangeListener { it.setLocation(Location.OVERWORLD) })
    }
}