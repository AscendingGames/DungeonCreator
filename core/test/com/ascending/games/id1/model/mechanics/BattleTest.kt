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

    @Test
    fun fight() {
        hero.stats[StatType.CURRENT_HP.name] = 2f
        hero.stats[StatType.ATTACK.name] = 1f
        hero.stats[StatType.EXP.name] = StatService().getNextExp(hero.stats) - 1f
        monster.stats[StatType.CURRENT_HP.name] = 1f
        monster.stats[StatType.ATTACK.name] = 1f
        battle.fight()
        assertEquals(1f, hero.stats[StatType.CURRENT_HP.name])
        assertEquals(2f, hero.stats[StatType.LEVEL.name])
        assertEquals(0f, monster.stats[StatType.CURRENT_HP.name])
    }
}