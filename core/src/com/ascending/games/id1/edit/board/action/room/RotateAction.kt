package com.ascending.games.id1.edit.board.action.room

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.Room

class RotateAction : IBoardAction {
    override fun execute(room : Room, board : Board) {
        for (i in 0..3) {
            room.rotate()
            if(!board.isRoomOverlapping(room) && board.isRoomInBounds(room)) {
                break
            }
        }
    }
}