package com.ascending.games.lib.model.pathfinding

interface IDistanceEstimator<Node> {
    fun estimateDistance(node1 : Node, node2 : Node) : Float
}