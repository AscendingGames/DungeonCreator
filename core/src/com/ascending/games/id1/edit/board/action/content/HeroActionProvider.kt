package com.ascending.games.id1.edit.board.action.content

import com.ascending.games.id1.edit.board.BoardDomain
import com.ascending.games.id1.model.board.*
import com.ascending.games.id1.model.mechanics.Battle
import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.pathfinding.Pathfinder
import kotlin.properties.Delegates

class HeroActionProvider(val hero : Hero) : IRoomContentActionProvider {

    lateinit var lastRoom : Room

    override fun getNextActions(boardDomain : BoardDomain) : List<IRoomContentAction> {
        if (hero.spawned) {
            if (hero.roomElement.room.isCleared) {
                return moveToRandomNeighbourRoom(boardDomain)
            } else {
                if (hero.roomElement.roomContents.isEmpty()) {
                    val randomRoomElement = hero.roomElement.room.roomElements.shuffled().last()
                    return moveToRoomElement(boardDomain, randomRoomElement)
                } else {
                    return clearRoom()
                }
            }
        }

        return listOf()
    }

    private fun moveToRandomNeighbourRoom(boardDomain: BoardDomain) : List<IRoomContentAction> {
        var neighbouringRooms = boardDomain.board.getNeighbours(hero.roomElement.room)
        if (neighbouringRooms.isEmpty()) return listOf()

        if (neighbouringRooms.size > 1) neighbouringRooms -= lastRoom

        val unclearedNeighbouringRooms = neighbouringRooms.filter { !it.isCleared }
        if (!unclearedNeighbouringRooms.isEmpty()) {
            neighbouringRooms = unclearedNeighbouringRooms.toSet()
        }

        val targetRoomElement = neighbouringRooms.shuffled().last().roomElements.shuffled().last()
        lastRoom = targetRoomElement.room
        return moveToRoomElement(boardDomain, targetRoomElement)
    }

    private fun moveToRoomElement(boardDomain: BoardDomain, roomElement: RoomElement) : List<IRoomContentAction> {
        val path = Pathfinder<RoomElement>(boardDomain.board, RoomElementDistanceEstimator()).getPath(hero.roomElement, roomElement)
        return listOf(ComposedRoomContentAction(path.map { MoveContentAction(hero, it) }))
    }

    private fun clearRoom() : List<IRoomContentAction> {
        val roomContent = hero.roomElement.roomContents[0]
        if (roomContent is Monster) {
            return listOf(BattleAction(Battle(hero, roomContent)))
        } else if (roomContent is Crystal){
            return listOf(ConsumeCrystalAction(hero, roomContent))
        } else {
            return listOf()
        }
    }
}