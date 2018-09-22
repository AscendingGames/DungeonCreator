package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.mechanics.Ritual
import com.ascending.games.id1.model.world.Location
import com.ascending.games.id1.model.world.PlayerService
import com.ascending.games.id1.view.mechanics.RitualLabelProvider

class RitualPlaceView(worldScreen: WorldScreen) : ALocationView(worldScreen, RITUAL_TEXT) {
    companion object {
        const val RITUAL_TEXT =
                "Your followers have gathered here in prayer.\n"+
                "By sacrificing their blood and worldy possessions,\n"+
                "they hope to give you newfound powers to guide them."
    }

    private val buttonBack = createBackButton(Location.SHRINE)
    private val ritualLabelProvider = RitualLabelProvider()
    private val playerService = PlayerService()

    init {
        locationTable.add(buttonBack)
    }

    override fun show() {
        super.show()
        player.enabledRituals.forEach {ritual ->
            val ritualButton = createTextButton(ritualLabelProvider.getValue(Ritual.valueOf(ritual))) {
                playerService.performRitual(player, Ritual.valueOf(ritual))
                hide()
                show()
            }
            locationTable.row().pad(100f)
            locationTable.add(ritualButton)
        }
    }

    override fun hide() {
        super.hide()
        locationTable.clearChildren()
        locationTable.add(descriptionLabel)
        locationTable.row().pad(100f)
        locationTable.add(buttonBack)
    }
}