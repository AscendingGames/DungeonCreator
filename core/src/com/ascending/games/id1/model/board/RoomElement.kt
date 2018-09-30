package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.data.ObservableList
import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.geometry.Direction4
import com.ascending.games.lib.model.geometry.IHierarchical2
import com.ascending.games.lib.model.geometry.IRectangle2
import com.badlogic.gdx.math.Vector2

class RoomElement(var roomRelativeCoord : Coord2) : IRectangle2, IHierarchical2{
    override val relativePosition: Vector2
        get() = Vector2(roomRelativeCoord.x.toFloat(), roomRelativeCoord.y.toFloat())
    override val parent: Any?
        get() = room
    override val size = Vector2(1f,1f)

    lateinit var room : Room
    val clearables = ObservableList<AClearable>(mutableListOf())
    val walls  = ObservableList<Wall>(mutableListOf())

    val closedWalls : List<Wall>
        get() =  walls.filter { it.wallState == WallState.CLOSED }
    val boardCoord : Coord2
        get() = Coord2(position.x.toInt(), position.y.toInt())

    fun copy() : RoomElement {
        return RoomElement(roomRelativeCoord.copy())
    }

    fun rotate() {
        roomRelativeCoord.rotate()
        walls.forEach { it.direction = it.direction.rotateRight() }
    }

    fun isOpen(direction : Direction4) : Boolean {
        return walls.none { it.direction == direction }
    }
}