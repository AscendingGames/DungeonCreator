package com.ascending.games.lib.model.pathfinding

class Pathfinder<Node>(val graph : IGraph<Node>, val distanceEstimator : IDistanceEstimator<Node>) {
    fun getPath(startNode : Node, targetNode : Node) : List<Node> {
        val openList = mutableListOf<Pair<Node, Float>>(Pair(startNode, distanceEstimator.estimateDistance(startNode, targetNode)))
        val closedList = mutableSetOf<Node>()
        val mapNodeToPredecessor = mutableMapOf<Node, Node>()

        while (!closedList.contains(targetNode) && !openList.isEmpty()) {
            val rankedNode = openList.removeAt(0)
            expandNode(rankedNode, targetNode, openList, closedList, mapNodeToPredecessor)
        }

        return extractPath(startNode, targetNode, mapNodeToPredecessor)
    }

    private fun expandNode(rankedNode : Pair<Node, Float>, targetNode : Node, openList : MutableList<Pair<Node, Float>>, closedList : MutableSet<Node>, mapNodeToPredecessor : MutableMap<Node, Node>) {
        closedList.add(rankedNode.first)
        val neighbours = graph.getNeighbours(rankedNode.first)
        for (neighbour in neighbours) {
            if (!closedList.contains(neighbour)) {
                val estimatedDistance = distanceEstimator.estimateDistance(rankedNode.first, neighbour) + distanceEstimator.estimateDistance(neighbour, targetNode)
                val unrankedNode = Pair(neighbour, estimatedDistance)
                val nodeRanking = getOpenListRanking(unrankedNode, openList)
                mapNodeToPredecessor[neighbour] = rankedNode.first
                openList.add(nodeRanking, unrankedNode)
            }
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
        val path = mutableListOf<Node>(targetNode)
        var currentNode = mapNodeToPredecessor[targetNode]

        while (currentNode != null && currentNode != startNode) {
            path.add(currentNode)
            currentNode = mapNodeToPredecessor[currentNode]
        }

        return path.reversed()
    }
}