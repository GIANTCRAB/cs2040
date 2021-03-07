package maximizingwinnings

import java.lang.Float.NEGATIVE_INFINITY
import java.lang.Float.POSITIVE_INFINITY
import kotlin.math.max
import kotlin.math.min

class MatrixTransversal(
    private val matrix: Matrix,
    private val maxMoves: Int
) {
    fun beginScan(maximizing: Boolean): Int {
        return alphaBeta(0, maxMoves, maximizing)
    }

    /**
     * scan and transverse through an iterative manner
     */
    private fun alphaBeta(nodeId: Int, depth: Int, maximizing: Boolean, weight: Int = 0): Int {
        if (depth == 0) {
            return weight
        }
        var value: Int
        if (maximizing) {
            value = NEGATIVE_INFINITY.toInt()
            val nodeEdges: HashSet<Edge> = matrix.getNodeEdges(nodeId)
            nodeEdges.asSequence().forEach {
                val totalWeight = it.weight + weight
                value = max(value, alphaBeta(it.secondNodeId, depth - 1, maximizing, totalWeight))
            }
        } else {
            value = POSITIVE_INFINITY.toInt()
            val nodeEdges: HashSet<Edge> = matrix.getNodeEdges(nodeId)
            nodeEdges.asSequence().forEach {
                val totalWeight = it.weight + weight
                value = min(value, alphaBeta(it.secondNodeId, depth - 1, maximizing, totalWeight))
            }
        }

        return value
    }
}