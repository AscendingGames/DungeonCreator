package com.ascending.games.id1.edit.board.action

import com.ascending.games.id1.edit.board.BoardDomain

interface IBoardAction {
    fun execute(boardDomain : BoardDomain)
}