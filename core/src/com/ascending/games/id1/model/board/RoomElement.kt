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

    fun isOpen(direction : Direction4) : Boolean {
        return walls.none { it.direction == direction }
    }

    fun getClosedWalls() : List<Wall> {
        return walls.filter { it.wallState == WallState.CLOSED }
    }
    fun getBoardCoord() : Coord2 {
        return Coord2(this.getBoardX(), this.getBoardY())
    }

    fun getBoardX() : Int {
        return Math.ceil(room.position.x.toDouble() + position.x).toInt()
    }
    fun getBoardY() : Int {
        return Math.ceil(room.position.y.toDouble() + position.y).toInt()
    }
}