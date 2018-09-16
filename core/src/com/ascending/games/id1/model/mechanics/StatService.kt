package com.ascending.games.id1.model.mechanics

import com.ascending.games.lib.model.game.IStatType
import com.ascending.games.lib.model.game.IStats

class StatService {
    fun isDead(stats : IStats) : Boolean {
        return (stats.stats.get(StatType.CURRENT_HP) ?: 0f) <= 0f
    }

    fun applyDamage(attacker : IStats, defender : IStats) {
        val attack = attacker.stats[StatType.ATTACK] ?: 0f
        val defense = defender.stats[StatType.DEFENSE] ?: 0f
        val damage = Math.max(0f, attack - defense)
        defender.stats[StatType.CURRENT_HP] = Math.max(0f, (defender.stats[StatType.CURRENT_HP] ?: 0f) - damage)
    }

    fun reward(killer : IStats, killed : IStats) {
        killer.stats[StatType.EXP] = (killer.stats[StatType.EXP] ?: 0f) + (killed.stats[StatType.LEVEL] ?: 1f)

        if (hasLevelUp(killer)) {
            levelUp(killer)
        }
    }

    fun levelUp(stats : IStats) {
        val level = (stats.stats[StatType.LEVEL] ?: 1f).toInt()
        if (level % 10 == 0) {
            stats.stats[StatType.DEFENSE] = (stats.stats[StatType.DEFENSE] ?: 0f) + 1f
        } else if (level % 5 == 0) {
            stats.stats[StatType.ATTACK] = (stats.stats[StatType.ATTACK] ?: 0f) + 1f
        } else {
            stats.stats[StatType.MAX_HP] = (stats.stats[StatType.MAX_HP] ?: 0f) + 1f
        }

        stats.stats[StatType.EXP] = (stats.stats[StatType.EXP] ?: 0f) - getNextExp(stats.stats)
        stats.stats[StatType.LEVEL] = (stats.stats[StatType.LEVEL] ?: 1f) + 1
    }

    fun hasLevelUp(stats : IStats) : Boolean {
        return stats.stats[StatType.EXP] ?: 0f >= getNextExp(stats.stats)
    }

    fun getNextExp(stats : Map<IStatType, Float>) : Float {
        return ((stats[StatType.LEVEL] ?: 1f) + 1).let { it * it }
    }
}