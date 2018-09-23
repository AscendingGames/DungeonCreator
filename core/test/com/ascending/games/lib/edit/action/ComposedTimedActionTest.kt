package com.ascending.games.lib.edit.action

import org.junit.Test

import org.junit.Assert.*

class ComposedTimedActionTest {

    val MockAction = object : ITimedAction {
        override val canExecute = true

        override fun execute(delta: Float): Boolean {
            return true
        }
    }

    @Test
    fun canExecute() {
        assertFalse(ComposedTimedAction(listOf()).canExecute)
        val composedTimedAction = ComposedTimedAction(listOf(MockAction))
        assertTrue(composedTimedAction.canExecute)
        composedTimedAction.execute(0f)
        assertFalse(composedTimedAction.canExecute)
    }

    @Test
    fun execute() {
        val composedTimedAction = ComposedTimedAction(listOf(MockAction, MockAction))
        assertFalse(composedTimedAction.execute(1f))
        assertTrue(composedTimedAction.execute(1f))
        assertTrue(composedTimedAction.execute(1f))
    }
}