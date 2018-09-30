package com.ascending.games.engine.model.geometry

data class Coord2(var x : Int, var y : Int) {

    companion object {
        val ZERO = Coord2(0, 0)
    }

    fun rotate() : Coord2 {
        val oldX = this.x
        val oldY = this.y
        this.y = -oldX
        this.x = oldY

        return this
    }

    fun add(other : Coord2) : Coord2 {
        return Coord2(x + other.x, y + other.y)
    }

    fun distance(other : Coord2) : Float {
        return Math.sqrt(((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y)).toDouble()).toFloat()
    }
}