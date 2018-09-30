package com.ascending.games.engine.model.game

interface IStats {
    val stats : MutableMap<String, Float>
    val statModifiers : MutableList<IStatModifier>

    fun change(stat : String, value : Float, defaultValue : Float = 0f) {
        val newValue = (stats[stat] ?: defaultValue) + value
        stats[stat] = newValue
    }
}