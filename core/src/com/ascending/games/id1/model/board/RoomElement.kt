package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.game.IPosition2
import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.geometry.Direction4
import com.badlogic.gdx.math.Vector2

class RoomElement(var position : Coord2) : IPosition2 {
    lateinit var room : Room

    val roomContents = mutableListOf<ARoomContent>()
    var walls  = emptyList<Wall>()
    val boardX  : Int
        get() = Math.ceil(room.position.x.toDouble() + position.x).toInt()
    val boardY  : Int
        get() = Math.ceil(room.position.y.toDouble() + position.y).toInt()
    val boardCoord : Coord2
        get() = Coord2(boardX, boardY)
    override var relativePosition: Vector2
        get() = Vector2(position.x.toFloat(), position.y.toFloat())
        set(value) { position = Coord2(value.x.toInt(), value.y.toInt())}
    override val absolutePosition: Vector2
        get() = Vector2(boardX.toFloat(), boardY.toFloat())
    val closedWalls : List<Wall>
        get() =  walls.filter { it.wallState == WallState.CLOSED }

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
}