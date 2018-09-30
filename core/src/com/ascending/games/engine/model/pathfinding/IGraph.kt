package com.ascending.games.engine.model.pathfinding

interface IGraph<Node> {
    fun getNodes() : List<Node>
    fun getNeighbours(node : Node) : List<Node>
}