package com.ascending.games.id1.edit.board

import com.ascending.games.id1.edit.board.action.content.HeroActionProvider
import com.ascending.games.id1.edit.board.action.content.IRoomContentAction
import com.ascending.games.id1.edit.board.action.room.DropAction
import com.ascending.games.id1.edit.board.action.room.IBoardAction
import com.ascending.games.id1.model.board.ARoomContent
import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.Hero
import com.ascending.games.id1.model.board.Room
import com.badlogic.gdx.math.Vector2
import kotlin.math.roundToInt
import kotlin.properties.Delegates

class BoardDomain(val board: Board, private val roomFactory : IRoomFactory) {

    companion object {
        const val COUNT_WAITING_ROOMS = 3
    }

    var waitingRooms = List(COUNT_WAITING_ROOMS) { roomFactory.createRoom() }
    var currentRoom by Delegates.notNull<Room>()
    var time = 0f
    val hero : Hero = Hero()
    val heroActionProvider = HeroActionProvider(hero)

    val mapRoomContentToActionList = mutableMapOf<ARoomContent, List<IRoomContentAction>>()

    init {
        nextRoom()
    }

    fun update(time : Float) {
        var positionChanged = false
        for (room in board.rooms) {
            if (!board.hasRoomFallen(room)) {
                room.position.y -= time
                positionChanged = true
            } else {
                room.position.y = room.position.y.roundToInt().toFloat()
                board.openWallsNeighbouringDoors(room)
            }
        }

        if (!positionChanged) {

            if (!hero.spawned) {
                hero.spawn(board)
            }

            nextRoom()
        }

        for (row in 0 until board.height) {
            board.clearRowIfFull(row)
        }

        var heroActionList = mapRoomContentToActionList.getOrDefault(hero, emptyList())
        if (heroActionList.isEmpty()) {
            heroActionList = heroActionProvider.getNextActions(this)
            mapRoomContentToActionList.put(hero, heroActionList)
        }

        if (!heroActionList.isEmpty()) {
            val action = heroActionList[0]
            if (action.execute(this, time)) {
                mapRoomContentToActionList.put(hero, heroActionList.minus(action))
            }
        }
    }

    fun execute(action : IBoardAction) {
        action.execute(currentRoom,this)
    }

    fun getProjectedRoom() : Room {
        val projectedRoom = Room(currentRoom.roomElements.map { it -> it.copy() })
        DropAction().execute(projectedRoom, this)
        return projectedRoom
    }

    fun nextRoom() {
        currentRoom = waitingRooms[0]
        currentRoom.position = Vector2(((board.width - 1) / 2).toFloat(), board.height.toFloat() - 1)
        waitingRooms = waitingRooms.drop(1) + roomFactory.createRoom()
        board.rooms += currentRoom
    }
}