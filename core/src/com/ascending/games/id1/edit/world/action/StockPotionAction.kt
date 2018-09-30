package com.ascending.games.id1.edit.world.action

import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.id1.model.world.Player
import com.ascending.games.id1.model.world.PlayerService
import com.ascending.games.engine.edit.action.IAction

class StockPotionAction(private val player : Player) : com.ascending.games.engine.edit.action.IAction {
    private val playerService = PlayerService()

    override val canExecute: Boolean
        get() = (player.stats[StatType.COUNT_POTIONS.name] ?: 0f) < (player.stats[StatType.MAX_POTIONS.name] ?: 0f)
                && (player.stats[StatType.GOLD.name] ?: 0f) >= playerService.getPotionCosts(player)

    override fun execute(): Boolean {
        player.stats[StatType.COUNT_POTIONS.name] = (player.stats[StatType.COUNT_POTIONS.name] ?: 0f) + 1f
        player.stats[StatType.GOLD.name] = (player.stats[StatType.GOLD.name] ?: 0f) - playerService.getPotionCosts(player)
        return true
    }
}