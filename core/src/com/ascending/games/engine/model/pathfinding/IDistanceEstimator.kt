package com.ascending.games.engine.model.pathfinding

interface IDistanceEstimator<Node> {
    fun estimateDistance(node1 : Node, node2 : Node) : Float
}