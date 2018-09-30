package com.ascending.games.engine.edit.action

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.ARoomContent

interface ITimedAction {
    val canExecute : Boolean
    fun execute(delta : Float) : Boolean
}