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
            final Queue<ResearcherEvent> researcherQueue = processInput(br);

            final int totalUnlockSavings = processQueue(researcherQueue);

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                bw.write(String.valueOf(totalUnlockSavings));
                bw.flush();
            }
        }
    }

    public static Queue<ResearcherEvent> processInput(BufferedReader br) throws IOException {
        var inputInfo = new StringTokenizer(br.readLine());
        int numberOfResearchers = Integer.parseInt(inputInfo.nextToken());
        int inactivityLockMinutes = Integer.parseInt(inputInfo.nextToken());

        Queue<ResearcherEvent> researcherEventQueue = new PriorityQueue<>(numberOfResearchers * 3, new ResearcherEventComparator());

        for (int i = 0; i < numberOfResearchers; i++) {
            var researcherInfo = new StringTokenizer(br.readLine());
            var arrivalTime = Integer.parseInt(researcherInfo.nextToken());
            var researcherArrivalEvent = new ResearcherEvent(arrivalTime, EventTypes.ARRIVAL);
            var departureTime = Integer.parseInt(researcherInfo.nextToken()) + arrivalTime;
            var researcherDepartureEvent = new ResearcherEvent(departureTime, EventTypes.DEPARTURE);
            var researcherLockEvent = new ResearcherEvent(departureTime + inactivityLockMinutes, EventTypes.LOCK);

            researcherEventQueue.add(researcherArrivalEvent);
            researcherEventQueue.add(researcherDepartureEvent);
            researcherEventQueue.add(researcherLockEvent);
        }

        return researcherEventQueue;
    }

    public static int processQueue(Queue<ResearcherEvent> researcherQueue) {
        int unlockSavings = 0;
        int currentlyAvail = 0;

        while (researcherQueue.size() > 0) {
            var researcherEvent = researcherQueue.poll();

            if (researcherEvent.eventType == EventTypes.ARRIVAL) {
                if (currentlyAvail > 0) {
                    // use unlocked, increment savings
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

        return unlockSavings;
    }
}