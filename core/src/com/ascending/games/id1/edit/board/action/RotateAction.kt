package com.ascending.games.id1.edit.board.action

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.Room

class RotateAction : IBoardAction {
    override fun execute(room : Room, boardDomain: BoardDomain) {
        for (i in 0..3) {
            room.rotate()

            if(!boardDomain.board.isRoomOverlapping(room) && boardDomain.board.isRoomInBounds(room)) {
                break
            }
        }
    }
}