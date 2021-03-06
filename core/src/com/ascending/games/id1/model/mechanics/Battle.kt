package com.ascending.games.id1.model.mechanics

import com.ascending.games.id1.model.board.ARoomContent
import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.board.Monster

data class Battle(val hero : Hero, val monster : Monster) {

    private val statService = StatService()

    val winner : ARoomContent?
        get() =
            if (statService.isDead(hero)) monster
            else if (statService.isDead(monster)) hero
            else null
}