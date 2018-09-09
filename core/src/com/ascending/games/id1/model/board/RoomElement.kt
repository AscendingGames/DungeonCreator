package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.geometry.Direction4

class RoomElement(var position : Coord2) {
    lateinit var room : Room

    var walls  = emptyList<Wall>()

    fun copy() : RoomElement {
        return RoomElement(position.copy())
    }

    fun rotate() {
        position.rotate()
        walls = walls.map { Wall(this, it.direction.rotateRight(), it.wallState)}
    }

    fun getClosedWalls() : List<Wall> {
        return walls.filter { it.wallState == WallState.CLOSED }
    }
}