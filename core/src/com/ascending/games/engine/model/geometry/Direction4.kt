package com.ascending.games.engine.model.geometry

enum class Direction4 {
    UP, RIGHT, DOWN, LEFT;

    fun toOffset() : Coord2 {
        return when (this) {
            UP -> Coord2(0, 1)
            RIGHT -> Coord2(1, 0)
            DOWN -> Coord2(0, -1)
            LEFT -> Coord2(-1, 0)
        }
    }

    fun rotateLeft() : Direction4 {
        return when (this) {
            UP -> Direction4.LEFT
            RIGHT -> Direction4.UP
            DOWN -> Direction4.RIGHT
            LEFT -> Direction4.DOWN
        }
    }

    fun rotateRight() : Direction4 {
        return when (this) {
            UP -> Direction4.RIGHT
            RIGHT -> Direction4.DOWN
            DOWN -> Direction4.LEFT
            LEFT -> Direction4.UP
        }
    }

    fun opposite() : Direction4 {
        return when (this) {
            UP -> Direction4.DOWN
            RIGHT -> Direction4.LEFT
            DOWN -> Direction4.UP
            LEFT -> Direction4.RIGHT
        }
    }
}