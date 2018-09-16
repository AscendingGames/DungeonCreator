package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.model.board.Crystal
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.lib.edit.action.ITimedAction
import com.ascending.games.lib.model.game.AGameObject

class ConsumeCrystalAction(private val gameObject : AGameObject, private val crystal : Crystal) : ITimedAction {
    override val canExecute : Boolean
        get() = crystal.roomElement.roomContents.contains(crystal)

    override fun execute(delta: Float): Boolean {
        crystal.roomElement.roomContents.remove(crystal)
        when (crystal.type) {
            Crystal.Type.HEALING -> gameObject.stats[StatType.CURRENT_HP] = gameObject.stats[StatType.MAX_HP] ?: 0f
            Crystal.Type.ATTACK -> gameObject.stats[StatType.ATTACK] = (gameObject.stats[StatType.ATTACK] ?: 0f) + 1f
        }
        return true
    }
}