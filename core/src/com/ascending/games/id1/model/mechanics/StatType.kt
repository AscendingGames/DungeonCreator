package com.ascending.games.id1.model.mechanics

enum class StatType {
    CURRENT_HP, MAX_HP, ATTACK, DEFENSE, SPEED, EXP, LEVEL, GOLD, WEAPON_LEVEL, ARMOR_LEVEL;

    val isPermanentStat : Boolean
        get() = PERMANENT_STATS.contains(this)

    companion object {
        val PERMANENT_STATS = listOf<StatType>(MAX_HP, EXP, LEVEL, GOLD)
    }
}