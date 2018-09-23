package com.ascending.games.id1.model.board

import com.ascending.games.id1.model.mechanics.StatType

class Monster(level : Int) : ARoomContent() {
    init {
        initStats(level)
    }

    private fun initStats(level : Int) {
        stats[StatType.CURRENT_HP.name] = level.toFloat()
        stats[StatType.MAX_HP.name] = level.toFloat()
        stats[StatType.ATTACK.name] = (level % 2).toFloat()
    }

    fun spawn(roomElement: RoomElement) {
        this.roomElement = roomElement
        this.roomElement.roomContents.add(this)
    }

}