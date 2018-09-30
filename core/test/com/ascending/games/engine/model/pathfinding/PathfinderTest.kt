package com.ascending.games.engine.model.pathfinding

import com.ascending.games.engine.model.pathfinding.IDistanceEstimator
import com.ascending.games.engine.model.pathfinding.IGraph
import com.ascending.games.engine.model.pathfinding.Pathfinder
import org.junit.Test

import org.junit.Assert.*

class PathfinderTest {

    private val graph = object : IGraph<Int> {
        override fun getNodes(): List<Int> {
            return listOf(0, 1,2,3)
        }

        override fun getNeighbours(node: Int): List<Int> {
            when (node) {
                0 -> return listOf(1)
                1 -> return listOf(0, 2)
                2 -> return listOf(3)
                else -> return listOf()
            }
        }
    }

    private val distanceEstimator = object : IDistanceEstimator<Int> {
        override fun estimateDistance(node1: Int, node2: Int): Float {
            return Math.abs(node1 - node2).toFloat()
        }
    }

    @Test
    fun getPath() {
        val pathfinder = Pathfinder<Int>(graph, distanceEstimator)
        val path = pathfinder.getPath(1, 3)
        assertEquals(listOf(2, 3), path)
    }
}