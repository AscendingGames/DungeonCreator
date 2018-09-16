package com.ascending.games.id1.model.board

import com.ascending.games.id1.model.mechanics.StatType

class Monster(level : Int) : ARoomContent() {
    init {
        stats[StatType.CURRENT_HP] = level.toFloat()
        stats[StatType.MAX_HP] = level.toFloat()
        stats[StatType.ATTACK] = (level % 2).toFloat()
    }

    fun spawn(roomElement: RoomElement) {
        this.roomElement = roomElement
        this.roomElement.roomContents.add(this)
    }
}