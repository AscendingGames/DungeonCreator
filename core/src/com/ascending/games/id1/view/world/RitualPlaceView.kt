package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.world.Location
import com.ascending.games.id1.model.world.PlayerService
import com.ascending.games.id1.view.mechanics.RitualLabelProvider
import com.badlogic.gdx.scenes.scene2d.ui.TextButton

class RitualPlaceView(worldScreen: WorldScreen) : ALocationView(worldScreen, RITUAL_TEXT) {
    companion object {
        const val RITUAL_TEXT =
                "Your followers have gathered here in prayer.\n"+
                "By sacrificing their blood and worldy possessions,\n"+
                "they hope to give you newfound powers to guide them."
    }

    private val buttonBack = createBackButton(Location.SHRINE)

    private val ritualButtons = mutableListOf<TextButton>()
    private val ritualLabelProvider = RitualLabelProvider()
    private val playerService = PlayerService()

    init {
        locationTable.add(buttonBack)
    }

    override fun show() {
        super.show()
        player.enabledRituals.forEach {ritual ->
            val ritualButton = createTextButton(ritualLabelProvider.getValue(ritual)) {
                playerService.performRitual(player, ritual)
                hide()
                show()
            }
            ritualButtons.add(ritualButton)
            locationTable.row().pad(100f)
            locationTable.add(ritualButton)
        }
    }

    override fun hide() {
        super.hide()
        ritualButtons.forEach { it.remove() }
        ritualButtons.clear()
    }
}