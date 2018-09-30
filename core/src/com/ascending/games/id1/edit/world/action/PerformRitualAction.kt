package com.ascending.games.id1.edit.world.action

import com.ascending.games.id1.model.mechanics.Ritual
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.id1.model.world.Player
import com.ascending.games.id1.model.world.PlayerService
import com.ascending.games.engine.edit.action.IAction

class PerformRitualAction(private val player : Player, private val ritual : Ritual) : com.ascending.games.engine.edit.action.IAction {
    private val playerService =  PlayerService()

    override val canExecute: Boolean
        get() = player.enabledRituals.contains(ritual.name)
                && (player.stats[StatType.GOLD.name] ?: 0f) >= ritual.goldCosts.toFloat()
                && (player.stats[StatType.MAX_HP.name] ?: 0f) > ritual.hpCosts.toFloat()

    override fun execute(): Boolean {
        with(player) {
            performedRituals.add(ritual.name)
            enabledRituals.remove(ritual.name)
            stats[StatType.GOLD.name] = (stats[StatType.GOLD.name] ?: 0f) - ritual.goldCosts.toFloat()
            stats[StatType.MAX_HP.name] = (stats[StatType.MAX_HP.name] ?: 0f) - ritual.hpCosts.toFloat()
            stats[StatType.CURRENT_HP.name] = (stats[StatType.CURRENT_HP.name] ?: 0f) - ritual.hpCosts.toFloat()
        }

        playerService.updateEnabledBlessings(player)

        return true
    }
}