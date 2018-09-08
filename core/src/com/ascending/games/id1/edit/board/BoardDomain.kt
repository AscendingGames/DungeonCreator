package com.ascending.games.id1.edit.board

import com.ascending.games.id1.model.board.Board
import com.ascending.games.id1.model.board.Room

class BoardDomain(val board: Board, val roomFactory : IRoomFactory) {

    var waitingRooms : Array<Room> = Array(3, { i -> roomFactory.createRoom() });
    var currentRoom: Room = spawnRoom()

    fun spawnRoom() : Room{
        val spawnedRoom = waitingRooms.get(0)
        waitingRooms = waitingRooms.copyOfRange(1, waitingRooms.size - 1).plus(roomFactory.createRoom())
        return spawnedRoom
    }
}