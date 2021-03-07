package maximizingwinnings

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
            var looping = true

            while (looping) {
                val nMatrixSize = br.readLine().toInt()

                if (nMatrixSize == 0) {
                    looping = false
                } else {
                    val matrix = Matrix(nMatrixSize * nMatrixSize)
                    for (i in 0 until nMatrixSize) {
                        val rowData = StringTokenizer(br.readLine())
                        for (j in 0 until nMatrixSize) {
                            val edgeWeight = rowData.nextToken().toInt()
                            matrix.addNewEdge(i, j, edgeWeight)
                        }
                    }
                    val mMaxMoves = br.readLine().toInt()

                    val matrixTransversal = MatrixTransversal(matrix, mMaxMoves)
                    bw.write(matrixTransversal.beginScan(true).toString())
                    bw.write(" ")
                    bw.write(matrixTransversal.beginScan(false).toString())
                    bw.write("\n")
                }
            }

            bw.flush()
        }
    }
}