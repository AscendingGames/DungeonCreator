package com.ascending.games.id1.edit.mechanics.action

import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.board.Monster
import com.ascending.games.id1.model.mechanics.Battle
import com.ascending.games.id1.model.mechanics.StatService
import com.ascending.games.id1.model.mechanics.StatType
import org.junit.Assert.assertEquals
import org.junit.Test

class FightActionTest {

    private val hero = Hero()
    private val monster = Monster(0)
    private val battle = Battle(hero, monster)

    @Test
    fun fight() {
        hero.stats[StatType.CURRENT_HP.name] = 2f
        hero.stats[StatType.ATTACK.name] = 1f
        hero.stats[StatType.EXP.name] = StatService().getNextExp(hero.stats) - 1f
        monster.stats[StatType.CURRENT_HP.name] = 1f
        monster.stats[StatType.ATTACK.name] = 1f
        FightAction(battle).execute()
        assertEquals(1f, hero.stats[StatType.CURRENT_HP.name])
        assertEquals(2f, hero.stats[StatType.LEVEL.name])
        assertEquals(0f, monster.stats[StatType.CURRENT_HP.name])
    }
}