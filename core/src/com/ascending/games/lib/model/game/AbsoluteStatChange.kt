package com.ascending.games.lib.model.game

import com.ascending.games.id1.model.mechanics.StatType

open class AbsoluteStatChange(private val statChanges : MutableMap<IStatType, Float>) : IStatModifier {
    override var appliedTo: IStats? = null

    override fun apply(stats: IStats) {
        appliedTo = stats
        statChanges.forEach { stat, value -> stats.stats.put(stat, (stats.stats.get(stat) ?: 0f) + value) }
    }

    override fun unapply() {
        appliedTo?.let { appliedTo -> statChanges.forEach { stat, value -> appliedTo.stats.put(stat, (appliedTo.stats.get(stat) ?: 0f) - value)  } }
    }
}