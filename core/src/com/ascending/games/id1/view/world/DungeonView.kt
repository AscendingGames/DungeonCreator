package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.world.Location
import com.badlogic.gdx.scenes.scene2d.ui.TextButton

class DungeonView(worldScreen: WorldScreen) : ALocationView(worldScreen, ENTRANCE_TEXT) {

    companion object {
        const val ENTRANCE_TEXT =
                "Laid out before you stands a labyrinth so deep even a being such as you cannot grasp\n" +
                "its full depth. An ambitious group of your followers has gathered here to challenge\n" +
                "these depths for the vast amount of riches hidden deep inside..."
    }

    private val buttonCurrentLevel = TextButton("", skin)
    private val buttonNextLevel : TextButton = TextButton("", skin)
    private val buttonBack : TextButton = TextButton("Back", skin)

    init {
        locationTable.add(buttonCurrentLevel)
        locationTable.row().pad(100f)
        locationTable.add(buttonNextLevel)
        locationTable.row().pad(100f)
        locationTable.add(buttonBack)

        buttonCurrentLevel.listeners.add(createChangeListener { it.startLevel(player.knownDepths) })
        buttonNextLevel.listeners.add(createChangeListener { it.startLevel(player.newDepths) })
        buttonBack.listeners.add(createChangeListener { it.setLocation(Location.OVERWORLD) })
    }

    override fun show() {
        val newDepths  = player.newDepths
        val knownDepths = player.knownDepths

        buttonCurrentLevel.label.setText("Enter known depths (Level $knownDepths)")
        buttonNextLevel.label.setText("Explore new depths (Level $newDepths)")

        if (newDepths == 1) buttonNextLevel.isDisabled = true

        super.show()
    }
}