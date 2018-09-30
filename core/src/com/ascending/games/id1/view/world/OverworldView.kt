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
    private val buttons = listOf(dungeonButton, shrineButton, cityButton)

    init {
        dungeonButton.setPosition(worldScreen.uiStage.width * 0.6f, worldScreen.uiStage.height * 0.65f)
        shrineButton.setPosition(worldScreen.uiStage.width * 0.15f, worldScreen.uiStage.height * 0.55f)
        cityButton.setPosition(worldScreen.uiStage.width * 0.4f, worldScreen.uiStage.height * 0.2f)
    }

    override fun show() {
        super.show()
        buttons.forEach { worldScreen.uiStage.addActor(it) }
    }

    override fun hide() {
        super.hide()
        buttons.forEach { it.remove() }
    }
}