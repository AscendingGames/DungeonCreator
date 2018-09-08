package com.ascending.games.id1.edit.board.action

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.Room
import com.ascending.games.lib.model.geometry.Coord2

class MoveAction(private val offset: Coord2) : IBoardAction {

    override fun execute(room : Room, boardDomain: BoardDomain) {
        room.position.x += offset.x.toFloat()
        room.position.y += offset.y.toFloat()
    }
}