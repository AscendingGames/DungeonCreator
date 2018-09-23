package com.ascending.games.id1.view.world

import com.ascending.games.id1.edit.world.action.EnhanceAction
import com.ascending.games.id1.model.world.Location

class SmithyView(worldScreen: WorldScreen) : ALocationView(worldScreen, SMITHY_VIEW) {
    companion object {
        const val SMITHY_VIEW =
                "The air is filled with heat. You see workers\n"+
                "tirelessly working metal. What would be the result\n"+
                "if you were to touch the minds of these crafters...?\n"
    }

    private val buttonBack = createBackButton(Location.CITY)
    private val buttonEnhanceWeapons = createTextButton("Enhance Weapons") { EnhanceAction.createEnhanceWeaponAction(player).execute(); updateDisableState() }
    private val buttonEnhanceArmor = createTextButton("Enhance Armor") { EnhanceAction.createEnhanceArmorAction(player).execute(); updateDisableState() }

    init {
        locationTable.add(buttonBack)
        locationTable.row().pad(100f)
        locationTable.add(buttonEnhanceWeapons)
        locationTable.row().pad(100f)
        locationTable.add(buttonEnhanceArmor)
    }

    fun updateDisableState() {
        buttonEnhanceWeapons.isDisabled = !EnhanceAction.createEnhanceWeaponAction(player).canExecute
        buttonEnhanceArmor.isDisabled = !EnhanceAction.createEnhanceArmorAction(player).canExecute
    }

    override fun show() {
        updateDisableState()
        super.show()
    }
}