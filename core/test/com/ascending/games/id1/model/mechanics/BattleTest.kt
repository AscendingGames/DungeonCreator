package com.ascending.games.id1.model.mechanics

import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.board.Monster
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BattleTest {

    private val hero = Hero()
    private val monster = Monster(0)
    private val battle = Battle(hero, monster)

    @Test
    fun winner() {
        assertEquals(monster, battle.winner)
        hero.stats[StatType.CURRENT_HP.name] = 1f
        assertEquals(hero, battle.winner)
        monster.stats[StatType.CURRENT_HP.name] = 1f
        assertNull(battle.winner)
    }
}