package com.ascending.games.engine.edit.action

class EmptyTimedAction : com.ascending.games.engine.edit.action.ITimedAction {
    override val canExecute = true
    override fun execute(delta: Float) = true
}