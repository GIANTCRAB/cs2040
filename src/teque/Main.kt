package teque

import java.io.*
import java.nio.charset.StandardCharsets
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author WOO HUIREN ( A0202242B )
 */
@Throws(IOException::class)
fun main(args: Array<String>) {
    val dataArray = ArrayList<Int>()

    // Begin reader
    BufferedReader(InputStreamReader(System.`in`)).use { br ->
        val commandCount = br.readLine().toInt()

        BufferedWriter(OutputStreamWriter(FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512).use { bw ->
            for (i in 0 until commandCount) {
                // Read the entire line of operation
                val operationLine = StringTokenizer(br.readLine())
                val operationCommand = operationLine.nextToken()
                val operationNumber = operationLine.nextToken().toInt()

                // Carry out the operation commands
                when (operationCommand) {
                    // O(1)
                    "push_back" -> {
                        dataArray.add(operationNumber)
                    }
                    // O(1)
                    "push_front" -> {
                        dataArray.add(0, operationNumber)
                    }
                    // O(N)
                    "push_middle" -> {
                        val median = ((dataArray.size) + 1) / 2;
                        dataArray.add(median, operationNumber)
                    }
                    "get" -> {
                        bw.write(dataArray[operationNumber].toString() + "\n")
                    }
                }
            }
            // Print out all the things
            bw.flush()
        }
    }
}