package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.world.Location

class ShrineView(worldScreen: WorldScreen) : ALocationView(worldScreen, SHRINE_TEXT) {
    companion object {
        const val SHRINE_TEXT =
                "This is your shrine, nothing but a humongous stone\n"+
                "with random inscriptions scribbled on it.\n"+
                "Whoever did this had not expected any results.\n" +
                "A creation of despair -- a creation of wishes..."
    }

    private val buttonBack = createBackButton()
    private val ritualsButton = createTextButton("Rituals") { it.setLocation(Location.RITUAL_PLACE) }
    private val blessingsButton = createTextButton("Blessings") { it.setLocation(Location.BLESSING_PLACE) }

    init {
        locationTable.add(buttonBack)
        locationTable.row().pad(100f)
        locationTable.add(ritualsButton)
        locationTable.row().pad(100f)
        locationTable.add(blessingsButton)
    }
}