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

            final TreeSet<EventTime> eventTimeQueue = makeQueue(br, numberOfResearchers);

            final int totalUnlockSavings = processQueue(eventTimeQueue, inactivityLockMinutes);

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                bw.write(String.valueOf(totalUnlockSavings));
                bw.flush();
            }
        }
    }

    public static TreeSet<EventTime> makeQueue(final BufferedReader br, final int numberOfResearchers) throws IOException {
        final TreeSet<EventTime> eventTimeQueue = new TreeSet<>(new EventTimeComparator());

        for (int i = 0; i < numberOfResearchers; i++) {
            final StringTokenizer researcherInfo = new StringTokenizer(br.readLine());
            final int arrivalTime = Integer.parseInt(researcherInfo.nextToken());
            final int departureTime = Integer.parseInt(researcherInfo.nextToken()) + arrivalTime;
            final Researcher researcher = new Researcher(arrivalTime, departureTime);

            eventTimeQueue.add(new ResearcherEvent(arrivalTime, departureTime, EventTypes.ARRIVAL, researcher));
        }

        return eventTimeQueue;
    }

    public static int processQueue(final TreeSet<EventTime> eventTimeQueue, final int inactivityLockMinutes) {
        final Queue<Computer> availableComputers = new PriorityQueue<>(new ComputerComparator());
        int unlockSavings = 0;

        while (!eventTimeQueue.isEmpty()) {
            final EventTime event = eventTimeQueue.pollFirst();
            if (event instanceof ComputerEvent) {
                final ComputerEvent computerEvent = (ComputerEvent) event;
                if (computerEvent.getEventType() == EventTypes.FREE) {
                    // Free up the computer as nobody is using it already
                    availableComputers.add(computerEvent.computer);
                }
            }

            if (event instanceof ResearcherEvent) {
                final ResearcherEvent researcherEvent = (ResearcherEvent) event;
                if (researcherEvent.getEventType() == EventTypes.ARRIVAL) {
                    final Researcher researcher = researcherEvent.researcher;
                    final Integer computerLockTime = researcher.departureTime + inactivityLockMinutes;

                    Computer computer = null;
                    // Find an available computer
                    while (!availableComputers.isEmpty()) {
                        computer = availableComputers.poll();
                        if (computer != null && computer.getExpiry() >= researcherEvent.getEventStart()) {
                            // Update its new locktime
                            computer.setExpiry(computerLockTime);
                            unlockSavings++;

                            break;
                        }
                    }

                    if (computer == null) {
                        // Create a new computer for use since there are no available computers
                        computer = new Computer(computerLockTime);
                    }

                    eventTimeQueue.add(new ComputerEvent(researcher.departureTime, researcher.departureTime, EventTypes.FREE, computer));
                }
            }
        }

        return unlockSavings;
    }
}