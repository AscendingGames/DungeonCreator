package com.ascending.games.id1.model.world

import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.mechanics.Ritual
import com.ascending.games.id1.model.mechanics.StatType

class PlayerService {

    companion object {
        const val INIT_HP = 10f
        const val INIT_ATTACK = 1f
        const val INIT_DEFENSE = 0f
        const val INIT_SPEED = 2f
        const val INIT_LEVEL = 1f
        const val INIT_GOLD = 100f
    }

    fun createInitialPlayer() : Player {
        val initialPlayer = Player()
        initialPlayer.stats[StatType.MAX_HP] = INIT_HP
        initialPlayer.stats[StatType.CURRENT_HP] = INIT_HP
        initialPlayer.stats[StatType.ATTACK] = INIT_ATTACK
        initialPlayer.stats[StatType.DEFENSE] = INIT_DEFENSE
        initialPlayer.stats[StatType.SPEED] = INIT_SPEED
        initialPlayer.stats[StatType.LEVEL] = INIT_LEVEL
        initialPlayer.stats[StatType.GOLD] = INIT_GOLD
        return initialPlayer
    }

    fun clearLevel(player : Player, hero : Hero, depth : Int) {
        hero.stats.forEach { statType, value -> if (statType is StatType && statType.isPermanentStat) player.stats.put(statType, value) }
        player.stats[StatType.CURRENT_HP] = player.stats[StatType.MAX_HP] ?: 0f
        if (depth == player.depth) {
            advanceDepth(player)
        }
    }

    fun advanceDepth(player : Player) {
        player.depth++
        updateEnabledRituals(player)
    }

    fun updateEnabledRituals(player : Player) {
        player.enabledRituals +=
                Ritual.values().filter { it.unlockDepth >= player.depth && !player.performedRituals.contains(it) }
    }

    fun performRitual(player : Player, ritual : Ritual) {
        with(player) {
            performedRituals.add(ritual)
            enabledRituals.remove(ritual)
            stats[StatType.GOLD] = (stats[StatType.GOLD] ?: 0f) - ritual.goldCosts.toFloat()
            stats[StatType.MAX_HP] = (stats[StatType.MAX_HP] ?: 0f) - ritual.hpCosts.toFloat()
            stats[StatType.CURRENT_HP] = (stats[StatType.CURRENT_HP] ?: 0f) - ritual.hpCosts.toFloat()
        }
    }

    fun isRitualEnabled(player : Player, ritual : Ritual) : Boolean {
        return player.enabledRituals.contains(ritual)
                && (player.stats[StatType.GOLD] ?: 0f) >= ritual.goldCosts.toFloat()
                && (player.stats[StatType.MAX_HP] ?: 0f) >= ritual.hpCosts.toFloat()
    }
}