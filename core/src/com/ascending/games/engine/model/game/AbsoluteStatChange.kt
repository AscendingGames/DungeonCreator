package com.ascending.games.engine.model.game

open class AbsoluteStatChange(private val statChanges : MutableMap<String, Float>) : IStatModifier {
    override var appliedTo: IStats? = null

    override fun apply(stats: IStats) {
        appliedTo = stats
        statChanges.forEach { stat, value -> stats.stats.put(stat, (stats.stats.get(stat) ?: 0f) + value) }
    }

    override fun unapply() {
        appliedTo?.let { appliedTo -> statChanges.forEach { stat, value -> appliedTo.stats.put(stat, (appliedTo.stats.get(stat) ?: 0f) - value)  } }
    }
}