package com.ascending.games.engine.model.pathfinding

class Pathfinder<Node>(val graph : IGraph<Node>, val distanceEstimator : IDistanceEstimator<Node>) {
    fun getPath(startNode : Node, targetNode : Node) : List<Node> {
        val openList = mutableListOf(startNode to distanceEstimator.estimateDistance(startNode, targetNode))
        val closedList = mutableSetOf<Node>()
        val mapNodeToPredecessor = mutableMapOf<Node, Node>()

        while (!closedList.contains(targetNode) && !openList.isEmpty()) {
            val rankedNode = openList.removeAt(0)
            closedList.add(rankedNode.first)

            val neighbours = getUnexploredNeighbours(closedList, rankedNode, targetNode)
            expandNeighbours(neighbours, openList)
            neighbours.forEach { mapNodeToPredecessor[it.first] = rankedNode.first }
        }

        return extractPath(startNode, targetNode, mapNodeToPredecessor)
    }

    fun getUnexploredNeighbours(closedList: MutableSet<Node>, rankedNode: Pair<Node, Float>, targetNode: Node) : List<Pair<Node, Float>> {
        return  graph.getNeighbours(rankedNode.first)
                .filter { !closedList.contains(it) }
                .map { it to distanceEstimator.estimateDistance(rankedNode.first, it) + distanceEstimator.estimateDistance(it, targetNode) }
    }

    private fun expandNeighbours(neighbours : List<Pair<Node, Float>>, openList : MutableList<Pair<Node, Float>>) {
        for (neighbour in neighbours) {
            val nodeRanking = getOpenListRanking(neighbour, openList)
            openList.add(nodeRanking, neighbour)
        }
    }

    private fun getOpenListRanking(unrankedNode : Pair<Node, Float>, openList : MutableList<Pair<Node, Float>>) : Int {
        for (i in 0 until openList.size) {
            val rankedNode = openList[i]
            if (unrankedNode.second < rankedNode.second) {
                return i
            }
        }

        return openList.size
    }

    private fun extractPath(startNode : Node, targetNode : Node, mapNodeToPredecessor : MutableMap<Node, Node>) : List<Node> {
        val path = mutableListOf(targetNode)
        var currentNode = mapNodeToPredecessor[targetNode]

        while (currentNode != null && currentNode != startNode) {
            path.add(currentNode)
            currentNode = mapNodeToPredecessor[currentNode]
        }

        return path.reversed()
    }
}