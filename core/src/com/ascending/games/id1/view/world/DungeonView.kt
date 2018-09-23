package com.ascending.games.id1.view.world

class DungeonView(worldScreen: WorldScreen) : ALocationView(worldScreen, ENTRANCE_TEXT) {

    companion object {
        const val ENTRANCE_TEXT =
                "Laid out before you stands a labyrinth so deep even a being such as you cannot grasp\n" +
                "its full depth. An ambitious group of your followers has gathered here to challenge\n" +
                "these depths for the vast amount of riches hidden deep inside..."
    }

    private val buttonCurrentLevel = createTextButton { it.startLevel(player.knownDepths) }
    private val buttonNextLevel = createTextButton { it.startLevel(player.newDepths) }
    private val buttonBack = createBackButton()

    init {
        locationTable.add(buttonCurrentLevel)
        locationTable.row().pad(100f)
        locationTable.add(buttonNextLevel)
        locationTable.row().pad(100f)
        locationTable.add(buttonBack)
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