package com.ascending.games.id1.model.board

import com.ascending.games.engine.model.geometry.Coord2
import com.ascending.games.engine.model.geometry.Direction4
import com.badlogic.gdx.math.Rectangle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WallTest {

    private val roomElement = RoomElement(Coord2.ZERO)


    @Test
    fun getRelativePosition() {
        for (direction in Direction4.values()) {
            val wall = Wall(roomElement, direction, WallState.CLOSED)
            assertTrue(Rectangle(0f, 0f, 1f, 1f).contains(wall.relativePosition))
        }
    }

    @Test
    fun getParent() {
        val wall = Wall(roomElement, Direction4.DOWN, WallState.CLOSED)
        assertEquals(roomElement, wall.parent)
    }

    @Test
    fun getSize() {
        for (direction in Direction4.values()) {
            val wall = Wall(roomElement, direction, WallState.CLOSED)
            assertTrue(Rectangle(0f, 0f, 1f, 1f).contains(wall.size))
        }
    }
}