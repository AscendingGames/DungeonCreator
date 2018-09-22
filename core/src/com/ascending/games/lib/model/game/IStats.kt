package com.ascending.games.lib.model.game

interface IStats {
    val stats : MutableMap<String, Float>
    val statModifiers : MutableList<IStatModifier>
}