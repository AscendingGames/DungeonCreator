package com.ascending.games.id1.model.mechanics

import com.ascending.games.lib.model.game.IStats
import com.ascending.games.lib.model.game.AbsoluteStatChange

class Damage(damage : Float) : AbsoluteStatChange(mutableMapOf(Pair(StatType.CURRENT_HP, -damage))) {
    override fun apply(stats: IStats) {
        super.apply(stats)
        stats.stats.get(StatType.CURRENT_HP)?.let { if (it < 0) stats.stats[StatType.CURRENT_HP] = 0f }
    }
}