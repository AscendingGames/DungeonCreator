package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.model.board.Crystal
import com.ascending.games.id1.model.mechanics.StatType
import com.ascending.games.engine.edit.action.ITimedAction
import com.ascending.games.engine.model.game.AGameObject

class ConsumeCrystalAction(private val gameObject : AGameObject, private val crystal : Crystal) : com.ascending.games.engine.edit.action.ITimedAction {
    override val canExecute : Boolean
        get() = crystal.roomElement.clearables.contains(crystal)

    override fun execute(delta: Float): Boolean {
        crystal.roomElement.clearables.remove(crystal)
        when (crystal.type) {
            Crystal.Type.HEALING -> gameObject.stats[StatType.CURRENT_HP.name] = gameObject.stats[StatType.MAX_HP.name] ?: 0f
            Crystal.Type.ATTACK -> gameObject.stats[StatType.ATTACK.name] = (gameObject.stats[StatType.ATTACK.name] ?: 0f) + 1f
        }
        return true
    }
}