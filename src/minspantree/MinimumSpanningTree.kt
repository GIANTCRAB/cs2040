package minspantree

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Prim's Lazy Priority Queue Minimum Spanning Tree
 */
class MinimumSpanningTree(private val nodeCount: Int, private val edgeCount: Int) {
    var totalCost = 0
    private val marked = BooleanArray(this.nodeCount)
    private val adjacencyList: HashMap<Int, MutableList<Edge>> = HashMap(this.nodeCount)
    private val edgeQueue: Queue<Edge> = PriorityQueue<Edge>(this.edgeCount, EdgeComparator())

    fun addNewEdge(firstNodeId: Int, secondNodeId: Int, weight: Int) {
        val newEdge = Edge(firstNodeId, secondNodeId, weight)
        this.addEdge(newEdge)
    }

    // Lazy removal, space time complexity: O(E log(E))
    fun getMst(): MutableList<Edge>? {
        val mst: MutableList<Edge> = ArrayList(this.edgeCount)
        this.totalCost = 0
        // pick the first node?
        scan(0)

        while (this.edgeQueue.isNotEmpty()) {
            val retrievedEdge = this.edgeQueue.poll()
            // Check if nodes are marked. If both are marked, the link is redundant
            if (!this.marked[retrievedEdge.firstNodeId] || !this.marked[retrievedEdge.secondNodeId]) {
                // Scan the adjacency of these 2 nodes
                if (!this.marked[retrievedEdge.firstNodeId]) {
                    scan(retrievedEdge.firstNodeId)
                }
                if (!this.marked[retrievedEdge.secondNodeId]) {
                    scan(retrievedEdge.secondNodeId)
                }

                // Add to MST
                mst.add(retrievedEdge)
                this.totalCost += retrievedEdge.weight
            }
        }

        // Check if all nodes are marked
        return if (this.marked.contains(false)) {
            null
        } else {
            // All nodes are marked
            mst
        }
    }

    private fun addEdge(edge: Edge) {
        val firstNodeAdjacency = this.adjacencyList.getOrDefault(edge.firstNodeId, ArrayList())
        firstNodeAdjacency.add(edge)
        this.adjacencyList[edge.firstNodeId] = firstNodeAdjacency
        val secondNodeAdjacency = this.adjacencyList.getOrDefault(edge.secondNodeId, ArrayList())
        secondNodeAdjacency.add(edge)
        this.adjacencyList[edge.secondNodeId] = secondNodeAdjacency
    }

    private fun scan(nodeId: Int) {
        this.marked[nodeId] = true
        this.adjacencyList[nodeId]?.asSequence()?.filter { !this.marked[it.firstNodeId] || !this.marked[it.secondNodeId] }?.forEach {
            this.edgeQueue.add(it)
        }
    }
}