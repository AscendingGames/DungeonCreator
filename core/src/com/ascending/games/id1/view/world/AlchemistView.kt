package com.ascending.games.id1.view.world

import com.ascending.games.id1.edit.world.action.EnhanceAction
import com.ascending.games.id1.edit.world.action.StockPotionAction
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.id1.model.world.Location

class AlchemistView(worldScreen: WorldScreen) : ALocationView(worldScreen, SMITHY_VIEW) {
    companion object {
        const val SMITHY_VIEW =
                "This place is drenched in the smell of bitter medicine.\n"+
                "Those educated in the arts of healing are working here\n"+
                "on new tinktures. If some of your divine knowledge were\n" +
                "to grace them, then maybe...?"
    }

    private val buttonBack = createBackButton(Location.CITY)
    private val buttonStockPotion = createTextButton("Stock Potion (0 / 3)") {  StockPotionAction(player).execute(); update() }
    private val buttonEnhanceMedicinePouch = createTextButton("Enhance Medicine Pouch") { EnhanceAction.createEnhanceMedicinePouchAction(player).execute(); update() }
    private val buttonEnhancePotions = createTextButton("Enhance Potions (20HP)") { EnhanceAction.createEnhancePotionAction(player).execute(); update() }

    init {
        locationTable.add(buttonBack)
        locationTable.row().pad(100f)
        locationTable.add(buttonStockPotion)
        locationTable.row().pad(100f)
        locationTable.add(buttonEnhanceMedicinePouch)
        locationTable.row().pad(100f)
        locationTable.add(buttonEnhancePotions)
    }

    fun update() {
        buttonStockPotion.isDisabled = !StockPotionAction(player).canExecute
        buttonEnhanceMedicinePouch.isDisabled = !EnhanceAction.createEnhanceMedicinePouchAction(player).canExecute
        buttonEnhancePotions.isDisabled = !EnhanceAction.createEnhancePotionAction(player).canExecute

        buttonStockPotion.setText("Stock Potion (${(player.stats[StatType.COUNT_POTIONS.name] ?: 0f).toInt()} / ${(player.stats[StatType.MAX_POTIONS.name] ?: 0f).toInt()})")
        buttonEnhancePotions.setText("Enhance Potions (${(player.stats[StatType.HP_PER_POTION.name] ?: 0f).toInt()}HP)")
    }

    override fun show() {
        update()
        super.show()
    }
}