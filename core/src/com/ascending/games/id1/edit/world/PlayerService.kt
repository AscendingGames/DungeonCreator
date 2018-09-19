package com.ascending.games.id1.edit.world

import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.id1.model.world.Player

class PlayerService {

    companion object {
        const val INIT_HP = 10f
        const val INIT_ATTACK = 1f
        const val INIT_DEFENSE = 0f
        const val INIT_SPEED = 2f
        const val INIT_LEVEL = 1f
    }

    fun createInitialPlayer() : Player {
        val initialPlayer = Player()
        initialPlayer.stats[StatType.MAX_HP] = INIT_HP
        initialPlayer.stats[StatType.CURRENT_HP] = INIT_HP
        initialPlayer.stats[StatType.ATTACK] = INIT_ATTACK
        initialPlayer.stats[StatType.DEFENSE] = INIT_DEFENSE
        initialPlayer.stats[StatType.SPEED] = INIT_SPEED
        initialPlayer.stats[StatType.LEVEL] = INIT_LEVEL
        return initialPlayer
    }
}