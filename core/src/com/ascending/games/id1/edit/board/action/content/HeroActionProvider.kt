package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.model.board.ARoomContent
import com.ascending.games.id1.model.board.Hero

class HeroActionProvider(val hero : Hero) : IRoomContentActionProvider {
    override fun getNextActions() : List<IRoomContentAction> {
        if (hero.spawned) {
            return listOf(MoveContentAction(hero, hero.roomElement))
        } else {
            return listOf()
        }
    }
}