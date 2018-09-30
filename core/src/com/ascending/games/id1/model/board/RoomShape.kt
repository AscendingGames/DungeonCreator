package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.data.ObservableList
import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.geometry.Direction4

data class RoomShape(val roomElements : List<Coord2>) {
    fun createRoom() : Room {
        val copiedRoomElements = ObservableList(roomElements.map { RoomElement(it.copy()) }.toMutableList())
        val createdRoom = Room(copiedRoomElements)
        for (roomElement in createdRoom.roomElements) {
            roomElement.walls.addAll(createWalls(roomElement, createdRoom))
        }

        return createdRoom
    }

    private fun createWalls(roomElement : RoomElement, room : Room) : List<Wall> {
        return Direction4.values()
                .filter{ dir -> room.roomElements.none { roomElement.roomRelativeCoord.add(dir.toOffset()) == it.roomRelativeCoord } }
                .map { Wall(roomElement, it, WallState.CLOSED) }
    }
}