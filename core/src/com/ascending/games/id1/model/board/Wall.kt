package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.geometry.Direction4
import com.ascending.games.lib.model.geometry.IHierarchical2
import com.ascending.games.lib.model.geometry.IRectangle2
import com.badlogic.gdx.math.Vector2

data class Wall(val roomElement : RoomElement, var direction : Direction4, var wallState : WallState) : IRectangle2, IHierarchical2 {
    override val relativePosition: Vector2
        get() = when (direction) {
            Direction4.LEFT -> Vector2(0f, 0f)
            Direction4.RIGHT -> Vector2(0.9f, 0f)
            Direction4.DOWN -> Vector2(0f, 0f)
            Direction4.UP -> Vector2(0f, 0.9f)
        }
    override val parent: Any?
        get() = roomElement
    override val size: Vector2
        get() = when (direction) {
            Direction4.LEFT -> Vector2(0.1f, 1f)
            Direction4.RIGHT -> Vector2(0.1f, 1f)
            Direction4.DOWN -> Vector2(1f, 0.1f)
            Direction4.UP -> Vector2(1f, 0.1f)
        }
}