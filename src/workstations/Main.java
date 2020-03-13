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
            final StringTokenizer inputInfo = new StringTokenizer(br.readLine());
            final int numberOfResearchers = Integer.parseInt(inputInfo.nextToken());
            final int inactivityLockMinutes = Integer.parseInt(inputInfo.nextToken());

            final Queue<ResearcherEvent> researcherEvents = makeQueue(br, numberOfResearchers);

            final int totalUnlockSavings = processQueue(researcherEvents, inactivityLockMinutes);

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                bw.write(String.valueOf(totalUnlockSavings));
                bw.flush();
            }
        }
    }

    public static Queue<ResearcherEvent> makeQueue(final BufferedReader br, final int numberOfResearchers) throws IOException {
        final Queue<ResearcherEvent> researcherEvents = new PriorityQueue<>(new EventTimeComparator());

        for (int i = 0; i < numberOfResearchers; i++) {
            final StringTokenizer researcherInfo = new StringTokenizer(br.readLine());
            final int arrivalTime = Integer.parseInt(researcherInfo.nextToken());
            final int departureTime = Integer.parseInt(researcherInfo.nextToken()) + arrivalTime;
            final Researcher researcher = new Researcher(arrivalTime, departureTime);

            researcherEvents.add(new ResearcherEvent(arrivalTime, departureTime, researcher));
        }

        return researcherEvents;
    }

    public static int processQueue(final Queue<ResearcherEvent> researcherEvents, final int inactivityLockMinutes) {
        final Queue<ComputerEvent> computerPriorityQueue = new PriorityQueue<>(new EventTimeComparator());
        int unlockSavings = 0;

        while (!researcherEvents.isEmpty()) {
            final ResearcherEvent researcherEvent = researcherEvents.poll();

            final Researcher researcher = researcherEvent.researcher;
            final Integer computerLockTime = researcher.departureTime + inactivityLockMinutes;

            Computer computer = null;
            // Find an available computer
            while (!computerPriorityQueue.isEmpty()) {
                final ComputerEvent computerEvent = computerPriorityQueue.peek();
                // Check if computer queue is even ready
                if (computerEvent.getEventStart() > researcherEvent.getEventStart()) {
                    // Not ready
                    break;
                }

                computerPriorityQueue.remove();

                if (computerEvent.getEventStart() <= researcherEvent.getEventStart()) {
                    if (computerEvent.getEventEnd() >= researcherEvent.getEventStart()) {
                        // in range
                        computer = computerEvent.computer;
                        unlockSavings++;
                        break;
                    }
                }
            }

            if (computer == null) {
                // Create a new computer for use since there are no available computers
                computer = new Computer();
            }

            computerPriorityQueue.add(new ComputerEvent(researcher.departureTime, computerLockTime, computer));
        }

        return unlockSavings;
    }
}