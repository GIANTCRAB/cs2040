package trainpassengers

import java.io.*
import java.nio.charset.StandardCharsets
import java.util.*

fun main(args: Array<String>) {
    var possible = true
    var trainCurrentPassengerCount = 0

    // Begin reader
    BufferedReader(InputStreamReader(System.`in`)).use { br ->
        val trainData = StringTokenizer(br.readLine())
        val trainTotalCapacity = trainData.nextToken().toInt()
        val trainStationStops = trainData.nextToken().toInt()

        for (i in 0 until trainStationStops) {
            val stationInfo = StringTokenizer(br.readLine())
            val arrivalCount = stationInfo.nextToken().toInt()
            val departureCount = stationInfo.nextToken().toInt()
            val stationaryCount = stationInfo.nextToken().toInt()

            trainCurrentPassengerCount = trainCurrentPassengerCount - arrivalCount + departureCount

            // More passengers than available capacity OR negative passenger count
            if (trainCurrentPassengerCount > trainTotalCapacity || trainCurrentPassengerCount < 0) {
                possible = false
                break
            }

            // Non-boarding passengers when there are available slots
            if (stationaryCount != 0 && trainCurrentPassengerCount != trainTotalCapacity) {
                possible = false
                break
            }

            // Last station
            if (i + 1 == trainStationStops) {
                // Passengers at last station, people waiting at last station
                if (departureCount != 0 || stationaryCount != 0) {
                    possible = false
                    break
                }

                // There are still passengers
                if (trainCurrentPassengerCount != 0) {
                    possible = false
                    break
                }
            }
        }
    }

    BufferedWriter(OutputStreamWriter(FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512).use { bw ->
        if (possible) {
            bw.write("possible")
        } else {
            bw.write("impossible")
        }

        bw.flush()
    }
}