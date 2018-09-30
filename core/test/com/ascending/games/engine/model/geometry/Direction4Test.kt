package com.ascending.games.engine.model.geometry

import com.ascending.games.engine.model.geometry.Direction4
import org.junit.Test

import org.junit.Assert.*

class Direction4Test {

    @Test
    fun rotateLeft() {
        assertEquals(Direction4.UP, Direction4.UP.rotateLeft().rotateLeft().rotateLeft().rotateLeft())
    }

    @Test
    fun rotateRight() {
        assertEquals(Direction4.UP, Direction4.UP.rotateRight().rotateRight().rotateRight().rotateRight())
    }

    @Test
    fun opposite() {
        assertEquals(Direction4.UP, Direction4.UP.opposite().opposite())
        assertEquals(Direction4.LEFT, Direction4.LEFT.opposite().opposite())
    }
}