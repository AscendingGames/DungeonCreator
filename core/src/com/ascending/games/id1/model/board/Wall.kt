package com.ascending.games.id1.model.board

import com.ascending.games.engine.model.geometry.Direction4
import com.ascending.games.engine.model.geometry.IHierarchical2
import com.ascending.games.engine.model.geometry.IRectangle2
import com.badlogic.gdx.math.Vector2

data class Wall(val roomElement : RoomElement, var direction : Direction4, var wallState : WallState) : IRectangle2, IHierarchical2 {

    companion object {
        const val WALL_SIZE = 0.1f
    }

    override val relativePosition: Vector2
        get() = when (direction) {
            Direction4.LEFT -> Vector2(0f, 0f)
            Direction4.DOWN -> Vector2(0f, 0f)
            Direction4.RIGHT -> Vector2(1f - WALL_SIZE, 0f)
            Direction4.UP -> Vector2(0f, 1f - WALL_SIZE)
        }
    override val parent: Any?
        get() = roomElement
    override val size: Vector2
        get() = when (direction) {
            Direction4.LEFT -> Vector2(WALL_SIZE, 1f)
            Direction4.RIGHT -> Vector2(WALL_SIZE, 1f)
            Direction4.DOWN -> Vector2(1f, WALL_SIZE)
            Direction4.UP -> Vector2(1f, WALL_SIZE)
        }
}