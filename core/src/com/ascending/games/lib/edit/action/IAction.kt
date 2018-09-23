package com.ascending.games.lib.edit.action

interface IAction {
    val canExecute : Boolean
    fun execute() : Boolean
}