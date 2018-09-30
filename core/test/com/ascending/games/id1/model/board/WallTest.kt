package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.geometry.Coord2
import com.ascending.games.lib.model.geometry.Direction4
import com.badlogic.gdx.math.Vector2
import org.junit.Assert.assertEquals
import org.junit.Test

class WallTest {

    private val roomElement = RoomElement(Coord2.ZERO)
    private val wall = Wall(roomElement, Direction4.DOWN, WallState.CLOSED)

    @Test
    fun getRelativePosition() {
        assertEquals(Vector2.Zero, wall.relativePosition)
    }

    @Test
    fun getParent() {
        assertEquals(roomElement, wall.parent)
    }

    @Test
    fun getSize() {
        assertEquals(Vector2(1f, Wall.WALL_SIZE), wall.size)
    }
}