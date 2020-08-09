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
    private val nodesArray: MutableList<Node> = ArrayList(this.nodeCount)
    private val adjacencyList: HashMap<Node, MutableList<Edge>> = HashMap(this.nodeCount)
    private val edgeQueue: Queue<Edge> = PriorityQueue<Edge>(this.edgeCount, EdgeComparator())

    init {
        for (i in 0 until this.nodeCount) {
            this.nodesArray.add(Node(i))
        }
    }

    fun addNewEdge(firstNodeId: Int, secondNodeId: Int, weight: Int) {
        val newEdge = Edge(this.nodesArray[firstNodeId], this.nodesArray[secondNodeId], weight)
        this.addEdge(newEdge)
    }

    // Lazy removal, space time complexity: O(E log(E))
    fun getMst(): MutableList<Edge>? {
        val mst: MutableList<Edge> = ArrayList(this.edgeCount)
        this.totalCost = 0
        // pick the first node?
        scan(this.nodesArray[0])

        while (this.edgeQueue.isNotEmpty()) {
            val retrievedEdge = this.edgeQueue.poll()
            // Check if nodes are marked. If both are marked, the link is redundant
            if (!this.marked[retrievedEdge.firstNode.id] || !this.marked[retrievedEdge.secondNode.id]) {
                // Scan the adjacency of these 2 nodes
                if (!this.marked[retrievedEdge.firstNode.id]) {
                    scan(retrievedEdge.firstNode)
                }
                if (!this.marked[retrievedEdge.secondNode.id]) {
                    scan(retrievedEdge.secondNode)
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
        val firstNodeAdjacency = this.adjacencyList.getOrDefault(edge.firstNode, ArrayList())
        firstNodeAdjacency.add(edge)
        this.adjacencyList[edge.firstNode] = firstNodeAdjacency
        val secondNodeAdjacency = this.adjacencyList.getOrDefault(edge.secondNode, ArrayList())
        secondNodeAdjacency.add(edge)
        this.adjacencyList[edge.secondNode] = secondNodeAdjacency
    }


    private fun scan(node: Node) {
        this.marked[node.id] = true
        this.adjacencyList[node]?.forEach {
            if (!this.marked[it.firstNode.id] || !this.marked[it.secondNode.id]) {
                this.edgeQueue.add(it)
            }
        }
    }
}