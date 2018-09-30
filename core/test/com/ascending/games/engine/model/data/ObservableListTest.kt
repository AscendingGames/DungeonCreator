package com.ascending.games.engine.model.data

import com.ascending.games.engine.model.data.ObservableList
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ObservableListTest {

    private val data = mutableListOf(1,2,3)
    private val list = ObservableList(data)

    @Test
    fun add() {
        list.onAdd += { index, element ->
            assertEquals(3, index)
            assertEquals(4, element)
        }
        list.add(4)
    }


    @Test
    fun addAll() {
        val callbacks = mutableListOf<Pair<Int, Int>>()
        list.onAdd += { index, element ->
            callbacks.add(Pair(index, element))
        }
        list.addAll(listOf(3, 4))
        assertEquals(listOf(Pair(3, 3), Pair(4, 4)), callbacks)
    }

    @Test
    fun clear() {
        val callbacks = mutableListOf<Int>()
        list.onRemove += { element ->
            callbacks.add(element)
        }
        list.clear()
        assertEquals(listOf(1,2,3), callbacks)
    }

    @Test
    fun remove() {
        list.onRemove += { element ->
            assertEquals(2, element)
        }
        list.remove(2)
    }

    @Test
    fun testRemoveAll() {
        val callbacks = mutableListOf<Int>()
        list.onRemove += { element ->
            callbacks.add(element)
        }
        list.removeAll(listOf(3, 4))
        assertEquals(listOf(3), callbacks)
    }

    @Test
    fun removeAt() {
        val callbacks = mutableListOf<Int>()
        list.onRemove += { element ->
            callbacks.add(element)
        }
        list.removeAt(2)
        assertEquals(listOf(3), callbacks)
    }

    @Test
    fun retainAll() {
        val callbacks = mutableListOf<Int>()
        list.onRemove += { element ->
            callbacks.add(element)
        }
        list.retainAll(listOf(1,3))
        assertEquals(listOf(2), callbacks)
    }

    @Test
    fun set() {
        list.onAdd += { index, element ->
            assertEquals(1, index)
            assertEquals(3, element)
        }
        list.onRemove += {  element ->
            assertEquals(2, element)
        }
        list[1] = 3
    }

    @Test
    fun size() {
        assertEquals(data.size, list.size)
    }

    @Test
    fun containsAll() {
        assertTrue(list.containsAll(data))
    }

    @Test
    fun lastIndexOf() {
        assertEquals(data.lastIndexOf(1), list.lastIndexOf(1))
    }

    @Test
    fun subList() {
        assertEquals(data.subList(0, 2), list.subList(0, 2))
    }
}