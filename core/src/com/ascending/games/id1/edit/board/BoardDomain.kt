package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.Room
import kotlin.properties.Delegates

class BoardDomain(val board: Board, private val roomFactory : IRoomFactory) {

    var waitingRooms = List(3) { roomFactory.createRoom() }
    var currentRoom : Room by Delegates.notNull<Room>()

    init {
        nextRoom()
    }

    fun nextRoom() {
        currentRoom = waitingRooms[0]
        waitingRooms = waitingRooms.drop(1) + roomFactory.createRoom()
        board.rooms += currentRoom
    }
}