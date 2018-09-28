package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.world.Location

class OverworldView(worldScreen: WorldScreen) : ALocationView(worldScreen, OVERWORLD_TEXT) {
    companion object {
        const val OVERWORLD_TEXT =
                "As you gaze downwards, you see the mortal world\n"+
                "unfolding before you..."
    }

    private val dungeonButton = createTextButton("Dungeon", "dungeon" ) { it.setLocation(Location.DUNGEON) }
    private val shrineButton = createTextButton("Shrine", "shrine" ) { it.setLocation(Location.SHRINE) }
    private val cityButton = createTextButton("City", "city" ) { it.setLocation(Location.CITY) }

    init {
        locationTable.add(dungeonButton)
        locationTable.row().pad(100f)
        locationTable.add(shrineButton)
        locationTable.row().pad(100f)
        locationTable.add(cityButton)
    }
}