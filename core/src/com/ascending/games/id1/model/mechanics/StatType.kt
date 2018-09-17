package com.ascending.games.id1.model.mechanics

import com.ascending.games.lib.model.game.IStatType

enum class StatType : IStatType {
    CURRENT_HP, MAX_HP, ATTACK, DEFENSE, SPEED, EXP, LEVEL, GOLD;

    val isPermanentStat : Boolean
        get() = PERMANENT_STATS.contains(this)

    companion object {
        val PERMANENT_STATS = listOf<StatType>(MAX_HP, EXP, LEVEL, GOLD)
    }
}