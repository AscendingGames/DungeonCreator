package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.world.PlayerService
import com.ascending.games.id1.view.mechanics.RitualLabelProvider
import com.badlogic.gdx.scenes.scene2d.ui.TextButton

class ShrineView(worldScreen: WorldScreen) : ALocationView(worldScreen, SHRINE_TEXT) {
    companion object {
        const val SHRINE_TEXT =
                "This is your shrine, nothing but a humongous stone\n"+
                "with random inscriptions scribbled on it.\n"+
                "Whoever did this had not expected any results.\n" +
                "A creation of despair -- a creation of wishes..."
    }

    private val buttonBack = createBackButton()
    private val ritualsButton = createTextButton("Rituals") { showRituals() }
    private val ritualButtons = mutableListOf<TextButton>()

    private val ritualLabelProvider = RitualLabelProvider()
    private val playerService = PlayerService()

    init {
        locationTable.add(buttonBack)
        locationTable.row().pad(100f)
        locationTable.add(ritualsButton)
    }

    fun showRituals() {
        player.enabledRituals.forEach {ritual ->
            val ritualButton = createTextButton(ritualLabelProvider.getValue(ritual)) {
                playerService.performRitual(player, ritual)
                ritualsButton.remove()
            }
            ritualButtons.add(ritualButton)
        }
    }

    fun hideRituals() {
        ritualButtons.forEach { it.remove() }
        ritualButtons.clear()
    }
}