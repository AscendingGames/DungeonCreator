package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.edit.mechanics.BattleDomain
import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.board.Monster
import com.ascending.games.id1.model.mechanics.Battle

class BattleAction(private val battle : Battle) : IRoomContentAction {
    private val battleDomain = BattleDomain(battle)

    override fun execute(boardDomain: BoardDomain, delta: Float): Boolean {
        if (battle.winner == null) {
            battleDomain.update(delta)
        }

        val winner = battle.winner
        if (winner == battle.hero) {
            battle.monster.roomElement.roomContents.remove(battle.monster)
        }

        return winner != null
    }

    override fun canExecute(boardDomain: BoardDomain): Boolean {
        return battle.monster.roomElement.roomContents.contains(battle.monster)
    }
}