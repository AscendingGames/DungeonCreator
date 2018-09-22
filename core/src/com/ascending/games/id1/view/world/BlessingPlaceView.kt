package com.ascending.games.id1.view.world

import com.ascending.games.id1.model.mechanics.Blessing
import com.ascending.games.id1.model.world.Location
import com.ascending.games.id1.model.world.PlayerService

class BlessingPlaceView(worldScreen: WorldScreen) : ALocationView(worldScreen, BLESSING_TEXT) {
    companion object {
        const val BLESSING_TEXT =
                "Your followers have gathered here in prayer.\n"+
                "They hope that their devotion to you will give,\n"+
                "them new powers."
    }

    private val buttonBack = createBackButton(Location.SHRINE)
    private val playerService = PlayerService()

    init {
        locationTable.add(buttonBack)
    }

    override fun show() {
        super.show()
        player.enabledBlessings.forEach {blessing ->
            val blessingButton = createTextButton(blessing) {
                playerService.grantBlessing(player, Blessing.valueOf(blessing))
                hide()
                show()
            }
            locationTable.row().pad(100f)
            locationTable.add(blessingButton)
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