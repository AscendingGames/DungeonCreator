package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.Crystal
import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.lib.model.game.AGameObject

class ConsumeCrystalAction(private val gameObject : AGameObject, private val crystal : Crystal) : IRoomContentAction {
    override fun execute(boardDomain: BoardDomain, delta: Float): Boolean {
        crystal.roomElement.roomContents.remove(crystal)
        when (crystal.type) {
            Crystal.Type.HEALING -> gameObject.stats[StatType.CURRENT_HP] = gameObject.stats[StatType.MAX_HP] ?: 0f
            Crystal.Type.ATTACK -> gameObject.stats[StatType.ATTACK] = (gameObject.stats[StatType.ATTACK] ?: 0f) + 1f
        }
        return true
    }

    override fun canExecute(boardDomain: BoardDomain): Boolean {
        return crystal.roomElement.roomContents.contains(crystal)
    }

}