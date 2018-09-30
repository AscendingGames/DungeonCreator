package com.ascending.games.engine.edit.action

interface IAction {
    val canExecute : Boolean
    fun execute() : Boolean
}