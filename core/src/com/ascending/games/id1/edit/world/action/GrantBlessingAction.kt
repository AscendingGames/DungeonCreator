package com.ascending.games.id1.edit.world.action

import com.ascending.games.id1.model.mechanics.Blessing
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.id1.model.world.Player
import com.ascending.games.id1.model.world.PlayerService
import com.ascending.games.lib.edit.action.IAction

class GrantBlessingAction(private val player : Player, private val blessing : Blessing) : IAction {
    private val playerService =  PlayerService()

    override val canExecute: Boolean
        get() = player.enabledBlessings.contains(blessing.name)
                && (player.stats[StatType.GOLD.name] ?: 0f) >= blessing.costs.toFloat()

    override fun execute(): Boolean {
        with(player) {
            val newLevel = (grantedBlessings[blessing.name] ?: 0) + 1
            grantedBlessings.put(blessing.name, newLevel)
            if (blessing.isCleared(newLevel)) enabledBlessings.remove(blessing.name)
            stats[StatType.GOLD.name] = (stats[StatType.GOLD.name] ?: 0f) - blessing.costs.toFloat()
            stats[blessing.stat.name] = (stats[blessing.stat.name] ?: 0f) + blessing.value
        }

        playerService.updateEnabledBlessings(player)

        return true
    }
}