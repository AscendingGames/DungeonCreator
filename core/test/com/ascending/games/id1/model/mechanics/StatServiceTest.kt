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
}