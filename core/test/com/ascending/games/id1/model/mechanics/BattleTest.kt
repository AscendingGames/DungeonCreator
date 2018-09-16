package com.ascending.games.id1.model.mechanics

import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.board.Monster
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.isA
import org.junit.Test

import org.junit.Assert.*

class BattleTest {

    private val hero = Hero()
    private val monster = Monster(0)
    private val battle = Battle(hero, monster)

    @Test
    fun winner() {
        assertEquals(monster, battle.winner)
        hero.stats[StatType.CURRENT_HP] = 1f
        assertEquals(hero, battle.winner)
        monster.stats[StatType.CURRENT_HP] = 1f
        assertNull(battle.winner)
    }

    @Test
    fun fight() {
        battle.fight()
        assertThat(hero.statModifiers, hasItem(isA(Damage::class.java)))
        assertThat(monster.statModifiers, hasItem(isA(Damage::class.java)))
    }
}