package com.ascending.games.id1.edit.board

data class DefaultRoomFactoryConfig(
        val numberDoors : IntRange,
        val numberMonsters : IntRange,
        val probHealingCrystal : Float) {

    companion object {
        val DEFAULT_NUMBER_DOORS = 2..4
        val DEFAULT_NUMBER_MONSTERS = 0..3
        const val DEFAULT_PROB_CRYSTAL = 0.1f

        fun createDefaultRoomFactoryConfig() : DefaultRoomFactoryConfig {
            return DefaultRoomFactoryConfig(DEFAULT_NUMBER_DOORS, DEFAULT_NUMBER_MONSTERS, DEFAULT_PROB_CRYSTAL)
        }
    }
}