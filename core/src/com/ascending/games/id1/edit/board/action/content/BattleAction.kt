package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.edit.mechanics.BattleDomain
import com.ascending.games.id1.model.mechanics.Battle
import com.ascending.games.lib.edit.action.ITimedAction

class BattleAction(private val battle : Battle) : ITimedAction {
    private val battleDomain = BattleDomain(battle)

    override val canExecute : Boolean
        get() = battle.monster.roomElement.clearables.contains(battle.monster)

    override fun execute(delta: Float): Boolean {
        if (battle.winner == null) {
            battleDomain.update(delta)
        }

        val winner = battle.winner
        if (winner == battle.hero) {
            battle.monster.roomElement.clearables.remove(battle.monster)
        }

        return winner != null
    }
}