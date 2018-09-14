package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.ARoomContent
import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.board.RoomElement
import com.ascending.games.id1.model.board.RoomElementDistanceEstimator
import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.pathfinding.Pathfinder

class HeroActionProvider(val hero : Hero) : IRoomContentActionProvider {
    override fun getNextActions(boardDomain : BoardDomain) : List<IRoomContentAction> {
        if (hero.spawned) {
            return moveToRandomNeighbourRoom(boardDomain)
        }

        return listOf()
    }

    private fun moveToRandomNeighbourRoom(boardDomain: BoardDomain) : List<IRoomContentAction> {
        val neighbouringRooms = boardDomain.board.getNeighbours(hero.roomElement.room)
        if (neighbouringRooms.isEmpty()) return listOf()

        val targetRoomElement = neighbouringRooms.shuffled().last().roomElements.shuffled().last()
        val path = Pathfinder<RoomElement>(boardDomain.board, RoomElementDistanceEstimator()).getPath(hero.roomElement, targetRoomElement)
        return listOf(ComposedRoomContentAction(path.map { MoveContentAction(hero, it) }))
    }
}