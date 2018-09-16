package com.ascending.games.id1.edit.board

data class DefaultRoomFactoryConfig(
        val numberDoors : IntRange,
        val numberMonsters : IntRange,
        val probHealingCrystal : Float,
        val minRoomsTillStairsDown : Int,
        val probStairsDown : Float) {

    companion object {
        private val DEFAULT_NUMBER_DOORS = 2..4
        private val DEFAULT_NUMBER_MONSTERS = 0..3
        private const val DEFAULT_PROB_CRYSTAL = 0.1f
        private const val DEFAULT_MIN_ROOMS_TILL_STAIRS_DOWN = 50
        private const val DEFAULT_PROB_STAIRS_DOWN = 0.2f

        fun createDefaultRoomFactoryConfig() : DefaultRoomFactoryConfig {
            return DefaultRoomFactoryConfig(
                    DEFAULT_NUMBER_DOORS,
                    DEFAULT_NUMBER_MONSTERS,
                    DEFAULT_PROB_CRYSTAL,
                    DEFAULT_MIN_ROOMS_TILL_STAIRS_DOWN,
                    DEFAULT_PROB_STAIRS_DOWN
            )
        }
    }
}