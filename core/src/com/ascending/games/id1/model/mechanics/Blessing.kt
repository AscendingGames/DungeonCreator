package com.ascending.games.id1.model.mechanics

enum class Blessing(val costs : Int, val maxNumber : Int, val stat : StatType, val value : Float, val requires : List<Blessing> = emptyList()) {
    Novice(100, 5, StatType.MAX_HP, 5f),
    Warrior(200, 10, StatType.MAX_HP, 10f, listOf(Novice)),
    Thief(150, 10, StatType.SPEED, 0.2f, listOf(Novice)),
    Mage(600, 10, StatType.ATTACK, 1f, listOf(Novice)),
    Knight(1000, 10, StatType.DEFENSE, 1f, listOf(Warrior)),
    Sage(5000, 10, StatType.ATTACK, 1f, listOf(Mage)),
    Assassin(3000, 10, StatType.ATTACK, 1f, listOf(Thief));

    fun isCleared(number : Int) : Boolean {
        return number == maxNumber
    }
}