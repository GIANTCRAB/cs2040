package maximizingwinnings

class Matrix(private val nodeCount: Int) {
    private val adjacencyMap: HashMap<Int, HashSet<Edge>> = HashMap()

    /**
     * weighted directed edge
     */
    fun addNewEdge(firstNodeId: Int, secondNodeId: Int, weight: Int) {
        // self pointing edge is allowed and has weights, need to be included
        val newEdge = Edge(firstNodeId, secondNodeId, weight)

        val firstNodeAdjacency = this.adjacencyMap.getOrDefault(firstNodeId, HashSet())
        firstNodeAdjacency.add(newEdge)
        this.adjacencyMap[firstNodeId] = firstNodeAdjacency
    }

    fun getNodeEdges(nodeId: Int): HashSet<Edge> {
        return this.adjacencyMap.getValue(nodeId)
    }

    fun getNodeCount(): Int {
        return this.nodeCount
    }
}