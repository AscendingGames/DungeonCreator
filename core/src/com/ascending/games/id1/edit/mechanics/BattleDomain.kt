package com.ascending.games.id1.edit.mechanics

import com.ascending.games.id1.model.mechanics.Battle

class BattleDomain(val battle : Battle) {
    companion object {
        const val TIME_PER_TURN = 1
    }

    private var time = 0f

    fun update(time : Float) {
        this.time += time
        if (this.time >= TIME_PER_TURN) {
            this.time -= TIME_PER_TURN

            battle.fight()
        }
    }
}