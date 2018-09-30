package com.ascending.games.lib.edit.action

interface ITimedActionProvider {
    fun getNextAction() : ITimedAction?
}