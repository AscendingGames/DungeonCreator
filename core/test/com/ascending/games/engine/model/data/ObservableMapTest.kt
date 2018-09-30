package com.ascending.games.engine.model.data

import com.ascending.games.engine.model.data.ObservableMap
import org.junit.Assert.assertEquals
import org.junit.Test

class ObservableMapTest {

    private val data = mutableMapOf(Pair(1,1), Pair(2,2), Pair(3,3))
    private val map = ObservableMap(data)

    @Test
    fun put() {
        map.onPut += { key, value ->
            assertEquals(4, key)
            assertEquals(4, value)
        }
        map.onChange += { key, value ->
            assertEquals(4, key)
            assertEquals(4, value)
        }
        map.put(4, 4)
    }

    @Test
    fun putAll() {
        val callbacks = mutableListOf<Pair<Int, Int>>()
        map.onPut += { key, value ->
            callbacks.add(Pair(key, value))
        }
        map.onChange += { key, value ->
            callbacks.add(Pair(key, value))
        }
        map.putAll(mutableMapOf(Pair(3, 4), Pair(4, 4)))
        assertEquals(listOf(Pair(3, 4), Pair(3, 4), Pair(4, 4), Pair(4, 4)), callbacks)
    }

    @Test
    fun clear() {
        val callbacks = mutableListOf<Pair<Int, Int>>()
        map.onRemove += { key, element ->
            callbacks.add(Pair(key, element))
        }
        map.onChange += { key, element ->
            callbacks.add(Pair(key, element))
        }
        map.clear()
        assertEquals(listOf(Pair(1, 1), Pair(1, 1), Pair(2, 2), Pair(2, 2), Pair(3, 3), Pair(3, 3)), callbacks)
    }

    @Test
    fun remove() {
        map.onRemove += { key, value ->
            assertEquals(2, key)
            assertEquals(2, value)
        }
        map.onChange += { key, value ->
            assertEquals(2, key)
            assertEquals(2, value)
        }
        map.remove(2)
    }

    @Test
    fun size() {
        assertEquals(data.size, map.size)
    }

    @Test
    fun keys() {
        assertEquals(data.keys, map.keys)
    }

    @Test
    fun values() {
        assertEquals(data.values, map.values)
    }

    @Test
    fun isEmpty() {
        assertEquals(data.isEmpty(), map.isEmpty())
    }
}