package com.ascending.games.lib.edit.action

class EmptyTimedAction : ITimedAction {
    override val canExecute = true
    override fun execute(delta: Float) = true
}