package com.ascending.games.engine.model.geometry

import com.ascending.games.engine.model.geometry.Coord2
import org.junit.Test

import org.junit.Assert.*

class Coord2Test {

    @Test
    fun rotate() {
        assertEquals(Coord2.ZERO, Coord2(0, 0).rotate())
        assertEquals(Coord2(0, -1), Coord2(1, 0).rotate())
        assertEquals(Coord2(-1, 0), Coord2(0, -1).rotate())
        assertEquals(Coord2(0, 1), Coord2(-1, 0).rotate())
    }
}