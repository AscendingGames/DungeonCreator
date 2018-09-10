package com.ascending.games.id1.edit.board.action.room

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.Room

class SlideAction(private val offset: Int) : IBoardAction {

    override fun execute(room : Room, boardDomain: BoardDomain) {
        val direction = Math.signum(offset.toFloat()).toInt()
        val absoluteOffset = Math.abs(offset)

        for (x in 0 until absoluteOffset) {
            room.position.x += direction

            if(boardDomain.board.isRoomOverlapping(room) || !boardDomain.board.isRoomInBounds(room)) {
                room.position.x -= direction
                break
            }
        }
    }
}