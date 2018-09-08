package com.ascending.games.id1.edit.board.action

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.Room
import com.ascending.games.id1.model.board.RoomElement
import com.ascending.games.lib.model.geometry.Coord2

class DropAction : IBoardAction {
    override fun execute(room : Room, boardDomain: BoardDomain) {
        room.position.y = room.roomElements.map { getDropY(it, boardDomain) }.min() ?: 0f
    }

    private fun getDropY(roomElement : RoomElement, boardDomain : BoardDomain) : Float {
        val coord = boardDomain.board.getBoardCoord(roomElement)
        for (row in 0 until coord.y) {
            if (boardDomain.board.getRoomElementAt(Coord2(coord.x, row)) == null) {
                return row.toFloat()
            }
        }

        return coord.y.toFloat()
    }
}