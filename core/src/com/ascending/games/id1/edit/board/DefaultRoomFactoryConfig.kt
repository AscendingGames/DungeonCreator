package com.ascending.games.id1.edit.board

data class DefaultRoomFactoryConfig(val minDoors : Int, val maxDoors : Int, val minMonsters : Int, val maxMonsters : Int) {
    companion object {
        const val DEFAULT_MIN_DOORS = 2
        const val DEFAULT_MAX_DOORS = 4

        const val DEFAULT_MIN_MONSTERS = 0
        const val DEFAULT_MAX_MONSTERS = 3

        fun createDefaultRoomFactoryConfig() : DefaultRoomFactoryConfig {
            return DefaultRoomFactoryConfig(DEFAULT_MIN_DOORS, DEFAULT_MAX_DOORS, DEFAULT_MIN_MONSTERS, DEFAULT_MAX_MONSTERS)
        }
    }
}