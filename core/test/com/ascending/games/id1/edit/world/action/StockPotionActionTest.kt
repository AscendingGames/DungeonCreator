package com.ascending.games.id1.edit.world.action

import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.id1.model.world.Player
import com.ascending.games.id1.model.world.PlayerService
import org.junit.Assert.*
import org.junit.Test

class StockPotionActionTest {

    val player = Player()
    val stockPotionAction = StockPotionAction(player)

    @Test
    fun getCanExecute() {
        assertFalse(stockPotionAction.canExecute)
        player.stats[StatType.GOLD.name] = PlayerService.COST_PER_POTION
        assertFalse(stockPotionAction.canExecute)
        player.stats[StatType.MAX_POTIONS.name] = 1f
        assertTrue(stockPotionAction.canExecute)
        stockPotionAction.execute()
        assertFalse(stockPotionAction.canExecute)
    }

    @Test
    fun execute() {
        player.stats[StatType.GOLD.name] = PlayerService.COST_PER_POTION
        stockPotionAction.execute()
        assertEquals(0f, player.stats[StatType.GOLD.name])
        assertEquals(1f, player.stats[StatType.COUNT_POTIONS.name])
    }
}