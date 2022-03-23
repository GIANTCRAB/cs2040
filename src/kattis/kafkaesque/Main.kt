package kattis.kafkaesque

import java.io.*
import java.nio.charset.StandardCharsets

@Throws(IOException::class)
fun main() {
    val clerkDesks: MutableList<Int> = ArrayList()
    var passesRequired = 1

    // Begin reader
    BufferedReader(InputStreamReader(System.`in`)).use { br ->
        val clerkDeskCount = br.readLine().toInt()

        for (i in 0 until clerkDeskCount) {
            // Read the entire line
            val deskNumber = br.readLine().toInt()

            clerkDesks.add(deskNumber)
        }

        var previousNumber = clerkDesks[0]
        for (i in 1 until clerkDeskCount) {
            if (clerkDesks[i] < previousNumber) {
                passesRequired++
            }

            previousNumber = clerkDesks[i]
        }
    }

    BufferedWriter(OutputStreamWriter(FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512).use { bw ->
        bw.write(passesRequired.toString())
        // Print out all the things
        bw.flush()
    }
}