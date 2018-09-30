package com.ascending.games.id1.model.mechanics

import com.ascending.games.engine.model.game.AGameObject
import org.junit.Assert.*
import org.junit.Test

class StatServiceTest {

    private val gameObject = object : AGameObject() {
        override val parent: Any?
            get() = null
    }

    private val statService = StatService()

    @Test
    fun isDead() {
        assertTrue(statService.isDead(gameObject))
        gameObject.stats[StatType.CURRENT_HP.name] = 1f
        assertFalse(statService.isDead(gameObject))
    }

    fun reward() {
        statService.reward(gameObject, gameObject)
        assertEquals(1f, gameObject.stats[StatType.EXP.name])
    }

    fun levelUp() {
        gameObject.stats[StatType.EXP.name] = statService.getNextExp(gameObject.stats)
        statService.levelUp(gameObject)
        assertEquals(0f, gameObject.stats[StatType.EXP.name])
        assertEquals(2f, gameObject.stats[StatType.LEVEL.name])
        assertEquals(1f, gameObject.stats[StatType.MAX_HP.name])
    }

    @Test
    fun hasLevelUp() {
        assertFalse(statService.hasLevelUp(gameObject))
        gameObject.stats[StatType.EXP.name] = statService.getNextExp(gameObject.stats)
        assertTrue(statService.hasLevelUp(gameObject))
    }

    @Test
    fun consumePotion() {
        gameObject.stats[StatType.COUNT_POTIONS.name] = 1f
        gameObject.stats[StatType.HP_PER_POTION.name] = 1f
        gameObject.stats[StatType.MAX_HP.name] = 1f
        gameObject.stats[StatType.CURRENT_HP.name] = 0f
        statService.consumePotion(gameObject)
        assertEquals(0f, gameObject.stats[StatType.COUNT_POTIONS.name])
        assertEquals(1f, gameObject.stats[StatType.CURRENT_HP.name])
        gameObject.stats[StatType.COUNT_POTIONS.name] = 1f
        statService.consumePotion(gameObject)
        assertEquals(1f, gameObject.stats[StatType.CURRENT_HP.name])
    }
}