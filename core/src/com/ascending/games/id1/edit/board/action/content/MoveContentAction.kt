package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.ARoomContent
import com.ascending.games.id1.model.board.RoomElement

class MoveContentAction(val aRoomContent: ARoomContent, val targetRoomElement : RoomElement) : IRoomContentAction {
    override fun execute(boardDomain: BoardDomain, delta: Float) : Boolean {
        aRoomContent.roomElement = targetRoomElement
        return true
    }
}