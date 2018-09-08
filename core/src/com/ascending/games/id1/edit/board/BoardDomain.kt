package com.ascending.games.id1.edit.board

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

        for (row in 0 until board.height - 1) {
            board.clearRowIfFull(row)
        }
    }

    fun rotateCurrentRoom() {
        currentRoom.rotate()
    }

    fun dropCurrentRoom() {
        currentRoom.position.y = 0f
    }

    fun moveCurrentRoom(offset : Coord2) {
        currentRoom.position.x += offset.x.toFloat()
        currentRoom.position.y += offset.y.toFloat()
    }

    fun getProjectedRoom() : Room {
        return currentRoom.copy()
    }

    fun nextRoom() {
        currentRoom = waitingRooms[0]
        currentRoom.position = Vector2((board.width / 2).toFloat(), board.height.toFloat())
        waitingRooms = waitingRooms.drop(1) + roomFactory.createRoom()
        board.rooms += currentRoom
    }
}