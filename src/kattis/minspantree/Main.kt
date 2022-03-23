package kattis.minspantree

import java.io.*
import java.nio.charset.StandardCharsets
import java.util.*

fun main(args: Array<String>) {
    // Begin reader
    BufferedReader(InputStreamReader(System.`in`)).use { br ->
        BufferedWriter(
            OutputStreamWriter(FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII),
            512
        ).use { bw ->
            val looping = true

            while (looping) {
                val nodeAndEdgeCount = StringTokenizer(br.readLine())
                val nodeCount = nodeAndEdgeCount.nextToken().toInt()
                val edgeCount = nodeAndEdgeCount.nextToken().toInt()

                if (nodeCount == 0 && edgeCount == 0) {
                    break
                } else {
                    if (nodeCount > 0 && edgeCount > 0) {
                        // Make sure edges is at least n-1
                        if (nodeCount <= edgeCount + 1) {
                            val minimumSpanningTree = MinimumSpanningTree(nodeCount, edgeCount)

                            // Loop through edges
                            for (i in 0 until edgeCount) {
                                val edgeInfo = StringTokenizer(br.readLine())
                                val firstNodeId = edgeInfo.nextToken().toInt()
                                val secondNodeId = edgeInfo.nextToken().toInt()
                                val edgeWeight = edgeInfo.nextToken().toInt()

                                minimumSpanningTree.addNewEdge(firstNodeId, secondNodeId, edgeWeight)
                            }

                            try {
                                val result = minimumSpanningTree.getMst()
                                bw.write(minimumSpanningTree.getTotalCost().toString() + "\n")
                                result.forEach {
                                    bw.write(it.firstNodeId.toString() + " " + it.secondNodeId.toString() + "\n")
                                }
                            } catch (e: UnreachableNode) {
                                bw.write("Impossible\n")
                            }
                        } else {
                            // Read through but do not construct since it will be an invalid MST
                            for (i in 0 until edgeCount) {
                                br.readLine()
                            }
                            bw.write("Impossible\n")
                        }
                    } else {
                        bw.write("Impossible\n")
                    }
                }
            }

            bw.flush()
        }
    }
}