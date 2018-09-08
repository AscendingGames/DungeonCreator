package com.ascending.games.id1.edit.board

import com.ascending.games.id1.edit.board.action.DropAction
import com.ascending.games.id1.edit.board.action.IBoardAction
import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.Room
import com.ascending.games.lib.model.geometry.Coord2
import com.badlogic.gdx.math.Vector2
import kotlin.properties.Delegates

class BoardDomain(val board: Board, private val roomFactory : IRoomFactory) {

    companion object {
        const val COUNT_WAITING_ROOMS = 3
    }

    var waitingRooms = List(COUNT_WAITING_ROOMS) { roomFactory.createRoom() }
    var currentRoom : Room by Delegates.notNull<Room>()
    var time = 0f

    init {
        nextRoom()
    }

    fun update(time : Float) {
        var positionChanged = false
        for (room in board.rooms) {
            if (!board.hasRoomFallen(room)) {
                room.position.y -= time
                positionChanged = true
            }
        }

        if (!positionChanged) {
            nextRoom()
        }

        for (row in 0 until board.height) {
            board.clearRowIfFull(row)
        }
    }

    fun execute(action : IBoardAction) {
        action.execute(currentRoom,this)
    }

    fun getProjectedRoom() : Room {
        val projectedRoom = currentRoom.copy()
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