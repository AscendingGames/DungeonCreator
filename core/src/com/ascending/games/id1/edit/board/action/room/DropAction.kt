package com.ascending.games.id1.edit.board.action.room

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.Room

class DropAction : IBoardAction {
    override fun execute(room : Room, board : Board) {
        while (room.position.y > 0) {
            room.position.y--

            if(board.isRoomOverlapping(room) || !board.isRoomInBounds(room)) {
                room.position.y++
                break
            }
        }

        room.position.y  = Math.ceil(room.position.y.toDouble()).toFloat()
    }
}