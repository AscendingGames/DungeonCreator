package com.ascending.games.id1.model.mechanics

import com.ascending.games.lib.model.game.AGameObject
import com.ascending.games.lib.model.game.IStatModifier
import com.ascending.games.lib.model.game.IStatType
import com.ascending.games.lib.model.game.IStats
import org.junit.Test

import org.junit.Assert.*

class StatServiceTest {

    private val gameObject = object : AGameObject() {
        override val parent: Any?
            get() = null
    }

    private val statService = StatService()

    @Test
    fun isDead() {
        assertTrue(statService.isDead(gameObject))
        gameObject.stats[StatType.CURRENT_HP] = 1f
        assertFalse(statService.isDead(gameObject))
    }

    fun reward() {
        statService.reward(gameObject, gameObject)
        assertEquals(1f, gameObject.stats[StatType.EXP])
    }

    fun levelUp() {
        gameObject.stats[StatType.EXP] = statService.getNextExp(gameObject.stats)
        statService.levelUp(gameObject)
        assertEquals(0f, gameObject.stats[StatType.EXP])
        assertEquals(2f, gameObject.stats[StatType.LEVEL])
        assertEquals(1f, gameObject.stats[StatType.MAX_HP])
    }

    @Test
    fun hasLevelUp() {
        assertFalse(statService.hasLevelUp(gameObject))
        gameObject.stats[StatType.EXP] = statService.getNextExp(gameObject.stats)
        assertTrue(statService.hasLevelUp(gameObject))
    }
}