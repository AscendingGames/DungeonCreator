package com.ascending.games.id1.edit.board.action

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.Room

class DropAction : IBoardAction {
    override fun execute(room : Room, boardDomain: BoardDomain) {
        boardDomain.currentRoom.position.y = 0f
    }
}