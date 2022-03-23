package kattis.teque

import java.io.*
import java.nio.charset.StandardCharsets
import java.util.*

/**
 * @author WOO HUIREN ( A0202242B )
 */
@Throws(IOException::class)
fun main(args: Array<String>) {
    val teque = Teque();

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
                        teque.addBack(operationNumber)
                    }
                    // O(1)
                    "push_front" -> {
                        teque.addFront(operationNumber)
                    }
                    // O(1)
                    "push_middle" -> {
                        teque.addMiddle(operationNumber)
                    }
                    "get" -> {
                        bw.write(teque.getElement(operationNumber) + "\n")
                    }
                }
            }
            // Print out all the things
            bw.flush()
        }
    }
}