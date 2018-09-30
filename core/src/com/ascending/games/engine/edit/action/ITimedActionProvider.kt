package com.ascending.games.engine.edit.action

interface ITimedActionProvider {
    fun getNextAction() : com.ascending.games.engine.edit.action.ITimedAction?
}