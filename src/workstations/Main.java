package workstations;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int unlockSavings = 0;
            int currentlyAvail = 0;

            final var inputInfo = new StringTokenizer(br.readLine());
            final int numberOfResearchers = Integer.parseInt(inputInfo.nextToken());
            final int inactivityLockMinutes = Integer.parseInt(inputInfo.nextToken());

            final Queue<ResearcherEvent> researcherQueue = new PriorityQueue<>(numberOfResearchers * 3, new ResearcherEventComparator());

            for (int i = 0; i < numberOfResearchers; i++) {
                var researcherInfo = new StringTokenizer(br.readLine());
                var arrivalTime = Integer.parseInt(researcherInfo.nextToken());
                var researcherArrivalEvent = new ResearcherEvent(arrivalTime, EventTypes.ARRIVAL);
                var departureTime = Integer.parseInt(researcherInfo.nextToken()) + arrivalTime;
                var researcherDepartureEvent = new ResearcherEvent(departureTime, EventTypes.DEPARTURE);
                var researcherLockEvent = new ResearcherEvent(departureTime + inactivityLockMinutes, EventTypes.LOCK);

                researcherQueue.add(researcherArrivalEvent);
                researcherQueue.add(researcherDepartureEvent);
                researcherQueue.add(researcherLockEvent);
            }

            while (researcherQueue.size() > 0) {
                var researcherEvent = researcherQueue.poll();

                if (researcherEvent.eventType == EventTypes.ARRIVAL) {
                    if (currentlyAvail > 0) {
                        // use unlocked
                        unlockSavings++;
                        currentlyAvail--;
                    } else {
                        // unlock one computer
                        currentlyAvail++;
                    }
                }

                if (researcherEvent.eventType == EventTypes.DEPARTURE) {
                    currentlyAvail++;
                }

                if (researcherEvent.eventType == EventTypes.LOCK) {
                    currentlyAvail--;
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                bw.write(String.valueOf(unlockSavings));
                bw.flush();
            }
        }
    }
}