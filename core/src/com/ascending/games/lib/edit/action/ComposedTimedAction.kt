package com.ascending.games.lib.edit.action

class ComposedTimedAction(private val actions : List<ITimedAction>) : ITimedAction {
    private var currentActionIndex = 0

    override val canExecute : Boolean
        get() = if (actions.isEmpty()) false else actions.subList(currentActionIndex, actions.size - 1).none { !it.canExecute }

    override fun execute(delta: Float): Boolean {
        if (currentActionIndex >= actions.size) return true

        if (actions[currentActionIndex].execute(delta)) {
            currentActionIndex++
        }

        return currentActionIndex == actions.size
    }
}