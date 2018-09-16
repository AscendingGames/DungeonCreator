package com.ascending.games.lib.model.game

interface IStats {
    val stats : MutableMap<IStatType, Float>
    val statModifiers : MutableList<IStatModifier>
}