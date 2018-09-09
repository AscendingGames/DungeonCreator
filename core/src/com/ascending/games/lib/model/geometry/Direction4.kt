package com.ascending.games.lib.model.geometry

enum class Direction4 {
    UP, RIGHT, DOWN, LEFT;

    fun toOffset() : Coord2 {
        when (this) {
            UP -> return Coord2(0, 1)
            RIGHT -> return Coord2(1, 0)
            DOWN -> return Coord2(0, -1)
            LEFT -> return Coord2(-1, 0)
        }
    }

    fun rotateLeft() : Direction4 {
        when (this) {
            UP -> return Direction4.LEFT
            RIGHT -> return Direction4.UP
            DOWN -> return Direction4.RIGHT
            LEFT -> return Direction4.DOWN
        }
    }

    fun rotateRight() : Direction4 {
        when (this) {
            UP -> return Direction4.RIGHT
            RIGHT -> return Direction4.DOWN
            DOWN -> return Direction4.LEFT
            LEFT -> return Direction4.UP
        }
    }
}