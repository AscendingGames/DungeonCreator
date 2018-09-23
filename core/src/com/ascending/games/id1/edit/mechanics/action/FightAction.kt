package com.ascending.games.id1.edit.mechanics.action

import com.ascending.games.id1.model.mechanics.Battle
import com.ascending.games.id1.model.mechanics.StatService
import com.ascending.games.lib.edit.action.IAction

class FightAction(private val battle : Battle) : IAction {
    private val statService = StatService()

    override val canExecute: Boolean
        get() = battle.winner == null

    override fun execute(): Boolean {
        val oldWinner = battle.winner
        if (oldWinner == null) {
            statService.applyDamage(battle.hero, battle.monster)
            statService.applyDamage(battle.monster, battle.hero)
        }

        val winner = battle.winner
        if (oldWinner == null && winner == battle.hero) {
            statService.reward(battle.hero, battle.monster)
        }

        return true
    }

}