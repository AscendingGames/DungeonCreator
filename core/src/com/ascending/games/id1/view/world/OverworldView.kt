package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.world.Location
import com.badlogic.gdx.scenes.scene2d.ui.TextButton

class OverworldView(worldScreen: WorldScreen) : ALocationView(worldScreen, OVERWORLD_TEXT) {
    companion object {
        const val OVERWORLD_TEXT =
                "As you gaze downwards, you see the mortal world\n"+
                "unfolding before you..."
    }

    private val dungeonButton = TextButton("Dungeon", skin, "overworld")
    private val shrineButton = TextButton("Shrine", skin, "overworld")
    private val cityButton = TextButton("City", skin, "overworld")

    init {
        locationTable.add(dungeonButton)
        locationTable.row().pad(100f)
        locationTable.add(shrineButton)
        locationTable.row().pad(100f)
        locationTable.add(cityButton)

        dungeonButton.listeners.add(createChangeListener { it.setLocation(Location.DUNGEON) })
        shrineButton.listeners.add(createChangeListener { it.setLocation(Location.SHRINE) })
        cityButton.listeners.add(createChangeListener { it.setLocation(Location.CITY) })
    }
}