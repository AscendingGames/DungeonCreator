package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.model.board.*
import com.ascending.games.id1.model.mechanics.Battle
import com.ascending.games.engine.edit.action.ComposedTimedAction
import com.ascending.games.engine.edit.action.EmptyTimedAction
import com.ascending.games.engine.edit.action.ITimedAction
import com.ascending.games.engine.edit.action.ITimedActionProvider
import com.ascending.games.engine.model.pathfinding.Pathfinder

class HeroActionProvider(val board : Board) : com.ascending.games.engine.edit.action.ITimedActionProvider {

    lateinit var lastRoom : Room

    private val hero = board.hero

    override fun getNextAction() : com.ascending.games.engine.edit.action.ITimedAction? {
        if (hero.spawned) {
            if (hero.roomElement.room.allRoomClearables.isEmpty()) {
                return moveToRandomNeighbourRoom()
            } else {
                return clearRoom()
            }
        }

        return null
    }

    private fun moveToRandomNeighbourRoom() : com.ascending.games.engine.edit.action.ITimedAction? {
        var neighbouringRooms = board.getNeighbours(hero.roomElement.room)
        if (neighbouringRooms.isEmpty()) return null

        if (neighbouringRooms.size > 1) neighbouringRooms -= lastRoom

        val unclearedNeighbouringRooms = neighbouringRooms.filter { !it.isCleared }
        if (!unclearedNeighbouringRooms.isEmpty()) {
            neighbouringRooms = unclearedNeighbouringRooms.toSet()
        }

        val targetRoomElement = neighbouringRooms.shuffled().last().roomElements.shuffled().last()
        lastRoom = targetRoomElement.room
        return moveToRoomElement(targetRoomElement)
    }

    private fun moveToRoomElement(roomElement: RoomElement) : com.ascending.games.engine.edit.action.ITimedAction {
        val path = Pathfinder<RoomElement>(board, RoomElementDistanceEstimator()).getPath(hero.roomElement, roomElement)
        return com.ascending.games.engine.edit.action.ComposedTimedAction(path.map { MoveContentAction(hero, it) })
    }

    private fun clearRoom() : com.ascending.games.engine.edit.action.ITimedAction {
        if (hero.roomElement.clearables.isEmpty()) {
            return moveToRoomElement(hero.roomElement.room.allRoomClearables[0].roomElement)
        } else {
            val roomContent = hero.roomElement.clearables[0]
            return when (roomContent) {
                is Monster -> BattleAction(Battle(hero, roomContent))
                is Crystal -> ConsumeCrystalAction(hero, roomContent)
                else -> com.ascending.games.engine.edit.action.EmptyTimedAction()
            }
        }
    }
}