package com.ascending.games.id1.model.board

import com.ascending.games.engine.model.pathfinding.IDistanceEstimator

class RoomElementDistanceEstimator() : IDistanceEstimator<RoomElement> {
    override fun estimateDistance(node1: RoomElement, node2: RoomElement): Float {
        val position1 = node1.position
        val position2 = node2.position
        return position1.dst(position2)
    }

}