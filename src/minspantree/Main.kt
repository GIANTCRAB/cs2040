package minspantree

import java.io.*
import java.nio.charset.StandardCharsets
import java.util.*

fun main(args: Array<String>) {
    // Begin reader
    BufferedReader(InputStreamReader(System.`in`)).use { br ->
        BufferedWriter(OutputStreamWriter(FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512).use { bw ->
            val looping = true

            while (looping) {
                val nodeAndEdgeCount = StringTokenizer(br.readLine())
                val nodeCount = nodeAndEdgeCount.nextToken().toInt()
                val edgeCount = nodeAndEdgeCount.nextToken().toInt()

                if (nodeCount == 0 && edgeCount == 0) {
                    break
                }

                // As long as edge count is at least (n - 1), valid to construct MST
                if (nodeCount != 0 && edgeCount != 0 && nodeCount - 1 <= edgeCount) {
                    val minimumSpanningTree = MinimumSpanningTree(nodeCount, edgeCount)

                    // Loop through edges
                    for (i in 0 until edgeCount) {
                        val edgeInfo = StringTokenizer(br.readLine())
                        val firstNodeId = edgeInfo.nextToken().toInt()
                        val secondNodeId = edgeInfo.nextToken().toInt()
                        val edgeWeight = edgeInfo.nextToken().toInt()

                        minimumSpanningTree.addNewEdge(firstNodeId, secondNodeId, edgeWeight)
                    }

                    val result = minimumSpanningTree.getMst()
                    if (result != null) {
                        bw.write(minimumSpanningTree.totalCost.toString() + "\n")
                        result.forEach {
                            bw.write(it.firstNodeId.toString() + " " + it.secondNodeId.toString() + "\n")
                        }
                    } else {
                        bw.write("Impossible\n")
                    }
                } else {
                    bw.write("Impossible\n")
                }
            }

            bw.flush()
        }
    }
}