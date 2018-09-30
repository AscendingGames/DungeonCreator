package com.ascending.games.engine.edit.action

class ComposedTimedAction(private val actions : List<com.ascending.games.engine.edit.action.ITimedAction>) : com.ascending.games.engine.edit.action.ITimedAction {
    private var currentActionIndex = 0

    override val canExecute : Boolean
        get() = if (currentActionIndex > actions.size - 1) false else actions.subList(currentActionIndex, actions.size - 1).none { !it.canExecute }

    override fun execute(delta: Float): Boolean {
        if (currentActionIndex >= actions.size) return true

        if (actions[currentActionIndex].execute(delta)) {
            currentActionIndex++
        }

        return currentActionIndex == actions.size
    }
}