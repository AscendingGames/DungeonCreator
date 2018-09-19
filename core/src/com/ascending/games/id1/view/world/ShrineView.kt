package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.world.Location
import com.badlogic.gdx.scenes.scene2d.ui.TextButton

class ShrineView(worldScreen: WorldScreen) : ALocationView(worldScreen, SHRINE_TEXT) {
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
        buttonBack.listeners.add(createChangeListener { it.setLocation(Location.OVERWORLD) })
    }
}