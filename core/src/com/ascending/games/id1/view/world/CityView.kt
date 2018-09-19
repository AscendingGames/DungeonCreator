package com.ascending.games.id1.view.world

class CityView(worldScreen: WorldScreen) : ALocationView(worldScreen, CITY_TEXT) {
    companion object {
        const val CITY_TEXT =
                "You observe the old city residing in your domain.\n"+
                "The wooden buildings are run-down and damaged,\n"+
                "most repairs only performed in a sloppy manner.\n" +
                "Clearly this place is ridden by poverty..."
    }

    private val buttonBack = createBackButton()

    init {
        locationTable.add(buttonBack)
    }
}