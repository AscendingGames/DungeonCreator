package com.ascending.games.lib.model.pathfinding

interface IGraph<Node> {
    fun getNodes() : List<Node>
    fun getNeighbours(node : Node) : List<Node>
}