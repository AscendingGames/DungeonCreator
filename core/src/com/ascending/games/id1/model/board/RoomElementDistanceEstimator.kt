package com.ascending.games.id1.model.board

import com.ascending.games.lib.model.pathfinding.IDistanceEstimator

class RoomElementDistanceEstimator() : IDistanceEstimator<RoomElement> {
    override fun estimateDistance(node1: RoomElement, node2: RoomElement): Float {
        val coord1 = node1.getBoardCoord()
        val coord2 = node2.getBoardCoord()
        return coord1.distance(coord2)
    }

}