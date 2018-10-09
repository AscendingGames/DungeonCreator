package com.ascending.games.id1.edit.board.action.room

import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.isA
import org.junit.Test

import org.junit.Assert.*

class GestureActionProviderTest {

    private val gestureActionProvider = GestureActionProvider()

    @Test
    fun zoom() {
        assertFalse(gestureActionProvider.zoom(0f, 0f))
    }

    @Test
    fun pan() {
        gestureActionProvider.pan(0f, 0f, 0f, GestureActionProvider.PAN_THRESHOLD_Y / 2)
        assertTrue(gestureActionProvider.actionBuffer.isEmpty())
        gestureActionProvider.pan(0f, 0f, 0f, GestureActionProvider.PAN_THRESHOLD_Y)
        assertThat(gestureActionProvider.actionBuffer, hasItem(isA(DropAction::class.java)))
        gestureActionProvider.pan(0f, 0f, 0f, GestureActionProvider.PAN_THRESHOLD_Y)
        assertEquals(2, gestureActionProvider.actionBuffer.size)

        assertTrue(gestureActionProvider.panStop(0f, 0f, 0, 0))

        gestureActionProvider.pan(0f, 0f, GestureActionProvider.PAN_THRESHOLD_X / 2, 0f)
        assertEquals(2, gestureActionProvider.actionBuffer.size)
        gestureActionProvider.pan(0f, 0f, GestureActionProvider.PAN_THRESHOLD_X / 2, 0f)
        assertThat(gestureActionProvider.actionBuffer, hasItem(isA(SlideAction::class.java)))
        assertEquals(3, gestureActionProvider.actionBuffer.size)
    }

    @Test
    fun tap() {
        assertTrue(gestureActionProvider.tap(0f, 0f, 0, 0))
        assertThat(gestureActionProvider.actionBuffer, hasItem(isA(RotateAction::class.java)))
    }

    @Test
    fun longPress() {
        assertFalse(gestureActionProvider.longPress(0f, 0f))
    }

    @Test
    fun touchDown() {
        assertFalse(gestureActionProvider.touchDown(0f, 0f, 0, 0))
    }

    @Test
    fun pinch() {
        assertFalse(gestureActionProvider.pinch(null, null, null, null))
    }

    @Test
    fun fling() {
        assertFalse(gestureActionProvider.fling(0f, 0f, 0))
    }
}