package com.ascending.games.id1.edit.board.action.room

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.Room

interface IBoardAction {
    fun execute(room : Room, boardDomain : BoardDomain)
}