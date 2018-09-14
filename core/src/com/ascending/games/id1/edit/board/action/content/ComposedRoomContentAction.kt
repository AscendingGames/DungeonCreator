package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.edit.board.BoardDomain

class ComposedRoomContentAction(private val actions : List<IRoomContentAction>) : IRoomContentAction {
    private var currentActionIndex = 0

    override fun canExecute(boardDomain: BoardDomain): Boolean {
        return actions.subList(currentActionIndex, actions.size - 1).none { !it.canExecute(boardDomain) }
    }

    override fun execute(boardDomain: BoardDomain, delta: Float): Boolean {
        if (currentActionIndex >= actions.size) return true

        if (actions[currentActionIndex].execute(boardDomain, delta)) {
            currentActionIndex++
        }

        return currentActionIndex == actions.size
    }
}