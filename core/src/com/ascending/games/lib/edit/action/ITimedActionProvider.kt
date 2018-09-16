package com.ascending.games.lib.edit.action

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.lib.edit.action.ITimedAction

interface ITimedActionProvider {
    fun getNextActions() : ITimedAction?
}