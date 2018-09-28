package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.model.board.*
import com.ascending.games.id1.model.mechanics.Battle
import com.ascending.games.lib.edit.action.ComposedTimedAction
import com.ascending.games.lib.edit.action.ITimedAction
import com.ascending.games.lib.edit.action.ITimedActionProvider
import com.ascending.games.lib.model.pathfinding.Pathfinder

class HeroActionProvider(val board : Board) : ITimedActionProvider {

    lateinit var lastRoom : Room

    private val hero = board.hero

    override fun getNextAction() : ITimedAction? {
        if (hero.spawned) {
            if (hero.roomElement.room.allRoomContents.isEmpty()) {
                return moveToRandomNeighbourRoom()
            } else {
                return clearRoom()
            }
        }

        return null
    }

    private fun moveToRandomNeighbourRoom() : ITimedAction? {
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

    private fun moveToRoomElement(roomElement: RoomElement) : ITimedAction {
        val path = Pathfinder<RoomElement>(board, RoomElementDistanceEstimator()).getPath(hero.roomElement, roomElement)
        return ComposedTimedAction(path.map { MoveContentAction(hero, it) })
    }

    private fun clearRoom() : ITimedAction? {
        val roomContent = hero.roomElement.roomContents[0]
        if (roomContent is Monster) {
            return BattleAction(Battle(hero, roomContent))
        } else if (roomContent is Crystal){
            return ConsumeCrystalAction(hero, roomContent)
        } else {
            return null
        }
    }
}