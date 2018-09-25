package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.geometry.Direction4
import com.ascending.games.lib.model.geometry.IPosition2
import com.badlogic.gdx.math.Vector2

class RoomElement(var roomRelativePosition : Coord2) : IPosition2 {
    lateinit var room : Room

    val roomContents = mutableListOf<ARoomContent>()
    var walls  = emptyList<Wall>()
    val boardX  : Int
        get() = Math.ceil(room.position.x.toDouble() + roomRelativePosition.x).toInt()
    val boardY  : Int
        get() = Math.ceil(room.position.y.toDouble() + roomRelativePosition.y).toInt()
    val boardCoord : Coord2
        get() = Coord2(boardX, boardY)
    override val position: Vector2
        get() = Vector2(boardX.toFloat(), boardY.toFloat())
    val closedWalls : List<Wall>
        get() =  walls.filter { it.wallState == WallState.CLOSED }

    fun copy() : RoomElement {
        return RoomElement(roomRelativePosition.copy())
    }

    fun rotate() {
        roomRelativePosition.rotate()
        walls = walls.map { Wall(this, it.direction.rotateRight(), it.wallState)}
    }

    fun isOpen(direction : Direction4) : Boolean {
        return walls.none { it.direction == direction }
    }
}