package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.mechanics.Ritual
import com.ascending.games.id1.model.world.Location

class CityView(worldScreen: WorldScreen) : ALocationView(worldScreen, CITY_TEXT) {
    companion object {
        const val CITY_TEXT =
                "You observe the old city residing in your domain.\n"+
                "The wooden buildings are run-down and damaged,\n"+
                "most repairs only performed in a sloppy manner.\n" +
                "Clearly this place is ridden by poverty..."
    }

    private val buttonBack = createBackButton()
    private val smithyButton = createTextButton("Smithy") { it.setLocation(Location.SMITHY) }
    private val alchemistButton = createTextButton("Alchemist") { it.setLocation(Location.ALCHEMIST) }

    init {
        locationTable.add(buttonBack)
    }

    override fun show() {
        if (player.performedRituals.contains(Ritual.RitualOfVisions.name)) {
            locationTable.row().pad(100f)
            locationTable.add(smithyButton)
        }

        if (player.performedRituals.contains(Ritual.RitualOfEnlightenment.name)) {
            locationTable.row().pad(100f)
            locationTable.add(alchemistButton)
        }

        super.show()
    }

    override fun hide() {
        super.hide()
        locationTable.clearChildren()
        locationTable.add(descriptionLabel)
        locationTable.row().pad(100f)
        locationTable.add(buttonBack)
    }
}