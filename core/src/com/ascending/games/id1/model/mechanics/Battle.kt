package com.ascending.games.id1.model.mechanics

import com.ascending.games.id1.model.board.ARoomContent
import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.board.Monster

class Battle(val hero : Hero, val monster : Monster) {

    private val statService = StatService()

    val winner : ARoomContent?
        get() =
            if (statService.isDead(hero)) monster
            else if (statService.isDead(monster)) hero
            else null

    fun fight() {
        hero.statModifiers.add(statService.createDamage(hero, monster))
        monster.statModifiers.add(statService.createDamage(hero, monster))
    }
}