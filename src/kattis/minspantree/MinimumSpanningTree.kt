package kattis.minspantree

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * Prim's Lazy Priority Queue Minimum Spanning Tree
 */
class MinimumSpanningTree(private val nodeCount: Int, private val edgeCount: Int) {
    private val marked = HashSet<Int>(this.nodeCount)
    private val adjacencyList: HashMap<Int, MutableList<Edge>> = HashMap()
    private val edgeQueue: Queue<Edge> = PriorityQueue(EdgeComparator())
    private var cachedMst: MutableList<Edge>? = null

    fun addNewEdge(firstNodeId: Int, secondNodeId: Int, weight: Int) {
        // self pointing edge is pointless, do not include
        if (firstNodeId != secondNodeId) {
            // "x < y" in requirement
            if (firstNodeId < secondNodeId) {
                val newEdge = Edge(firstNodeId, secondNodeId, weight)
                this.addEdge(newEdge)
            } else {
                val newEdge = Edge(secondNodeId, firstNodeId, weight)
                this.addEdge(newEdge)
            }
        }
    }

    /**
     * Lazy removal, space time complexity: O(E log(E))
     * @throws UnreachableNode
     */
    fun getMst(): MutableList<Edge> {
        val mst: MutableList<Edge> = ArrayList(this.edgeCount)
        val firstNodeInt = this.adjacencyList.keys.first()
        scan(firstNodeInt)

        while (this.edgeQueue.isNotEmpty()) {
            val retrievedEdge = this.edgeQueue.poll()
            // Check if nodes are marked. If both are marked, the link is redundant
            if (!this.marked.contains(retrievedEdge.firstNodeId) || !this.marked.contains(retrievedEdge.secondNodeId)) {
                // Scan the adjacency of these 2 nodes
                if (!this.marked.contains(retrievedEdge.firstNodeId)) {
                    scan(retrievedEdge.firstNodeId)
                }
                if (!this.marked.contains(retrievedEdge.secondNodeId)) {
                    scan(retrievedEdge.secondNodeId)
                }

                // Add to MST
                mst.add(retrievedEdge)
            }
        }

        // Check if all nodes are marked
        return if (this.marked.size >= this.nodeCount) {
            // All nodes are marked
            // Lexicographic order of integers needed
            mst.sortWith(EdgeSorterComparator())
            this.cachedMst = mst
            mst
        } else {
            throw UnreachableNode("There is a node that the MST could not reach")
        }
    }

    /**
     * @throws MstNotCalculated
     */
    fun getTotalCost(): Int {
        var totalCost = 0
        if (!this.cachedMst.isNullOrEmpty()) {
            this.cachedMst!!.asSequence().forEach {
                totalCost += it.weight
            }

            return totalCost
        }

        throw MstNotCalculated("Minimum spanning tree has not been calculated before attempting to retrieve total weight costs")
    }

    /**
     * Connect the first and second node by adding the new edge to the first and second node
     */
    private fun addEdge(edge: Edge) {
        val firstNodeAdjacency = this.adjacencyList.getOrDefault(edge.firstNodeId, ArrayList())
        firstNodeAdjacency.add(edge)
        this.adjacencyList[edge.firstNodeId] = firstNodeAdjacency
        val secondNodeAdjacency = this.adjacencyList.getOrDefault(edge.secondNodeId, ArrayList())
        secondNodeAdjacency.add(edge)
        this.adjacencyList[edge.secondNodeId] = secondNodeAdjacency
    }

    // scan the respective node's connected edges and add unmarked edges to the edge queue
    private fun scan(nodeId: Int) {
        this.marked.add(nodeId)
        if (this.adjacencyList.containsKey(nodeId)) {
            this.adjacencyList.getValue(nodeId).asSequence().forEach {
                this.edgeQueue.add(it)
            }
        }
    }
}