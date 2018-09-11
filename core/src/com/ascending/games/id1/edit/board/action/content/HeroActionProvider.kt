package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.ARoomContent
import com.ascending.games.id1.model.board.Hero

class HeroActionProvider(val hero : Hero) : IRoomContentActionProvider {
    override fun getNextActions(boardDomain : BoardDomain) : List<IRoomContentAction> {
        if (hero.spawned) {
            val neighbouringRooms = boardDomain.board.getNeighbouringRooms(hero.roomElement.room)
            if (neighbouringRooms.isEmpty()) {
                return listOf()
            } else {
                return listOf(MoveContentAction(hero, neighbouringRooms.shuffled().last().roomElements.shuffled().last()))
            }
        } else {
            return listOf()
        }
    }
}