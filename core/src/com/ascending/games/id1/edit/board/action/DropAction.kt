package com.ascending.games.id1.edit.board.action

import com.ascending.games.id1.edit.board.BoardDomain

class DropAction : IBoardAction {
    override fun execute(boardDomain: BoardDomain) {
        boardDomain.currentRoom.position.y = 0f
    }
}