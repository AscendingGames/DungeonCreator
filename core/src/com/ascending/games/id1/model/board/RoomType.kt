package com.ascending.games.id1.model.board

enum class RoomType {
    NORMAL, DANGER_HIGH, BOSS, TREASURY, TREASURY_HIGH, DANGER;

    fun getMonsterLevel(baseLevel : Int) : Int {
        return when (this) {
            DANGER_HIGH -> baseLevel + 2
            DANGER -> baseLevel + 1
            BOSS -> baseLevel * 2
            else -> baseLevel
        }
    }

    fun getExtraGoldPerTile() : Int {
        return when (this) {
            TREASURY_HIGH -> 5
            TREASURY -> 1
            else -> 0
        }
    }
}