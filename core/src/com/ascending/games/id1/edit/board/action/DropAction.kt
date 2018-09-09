package com.ascending.games.id1.edit.board.action

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.Room
import com.ascending.games.id1.model.board.RoomElement
import com.ascending.games.lib.model.geometry.Coord2

class DropAction : IBoardAction {
    override fun execute(room : Room, boardDomain: BoardDomain) {
        while (room.position.y > 0) {
            room.position.y--

            if(boardDomain.board.isRoomOverlapping(room) || !boardDomain.board.isRoomInBounds(room)) {
                room.position.y++
                break
            }
        }

        room.position.y  = Math.ceil(room.position.y.toDouble()).toFloat()
    }
}