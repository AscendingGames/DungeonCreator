package com.ascending.games.id1.edit.board.action

import com.ascending.games.id1.edit.board.BoardDomain

class RotateAction : IBoardAction {
    override fun execute(boardDomain: BoardDomain) {
        boardDomain.currentRoom.rotate()
    }
}