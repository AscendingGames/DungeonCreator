package com.ascending.games.id1.edit.board.action.room

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.Room

class SlideAction(private val offset: Int) : IBoardAction {

    override fun execute(room : Room, board : Board) {
        val direction = Math.signum(offset.toFloat()).toInt()
        val absoluteOffset = Math.abs(offset)

        for (x in 0 until absoluteOffset) {
            room.position.x += direction

            if(board.isRoomOverlapping(room) || !board.isRoomInBounds(room)) {
                room.position.x -= direction
                break
            }
        }
    }
}