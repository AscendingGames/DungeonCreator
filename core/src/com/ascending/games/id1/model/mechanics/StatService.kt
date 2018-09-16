package com.ascending.games.id1.model.mechanics

import com.ascending.games.lib.model.game.IStats

class StatService {
    fun isDead(stats : IStats) : Boolean {
        return (stats.stats.get(StatType.CURRENT_HP) ?: 0f) <= 0f
    }

    fun createDamage(attacker : IStats, defender : IStats) : Damage {
        val attack = attacker.stats[StatType.ATTACK] ?: 0f
        val defense = defender.stats[StatType.DEFENSE] ?: 0f
        return Damage(Math.max(0f, attack - defense))
    }
}