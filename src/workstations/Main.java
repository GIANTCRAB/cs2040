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

            final EventTime researcherArrivalEvent = new ResearcherEvent(arrivalTime, EventTypes.ARRIVAL, researcher);

            eventTimeQueue.add(researcherArrivalEvent);
        }

        return eventTimeQueue;
    }

    public static int processQueue(final TreeSet<EventTime> eventTimeQueue, final int inactivityLockMinutes) {
        final TreeSet<Computer> availableComputers = new TreeSet<>(new ComputerComparator());
        int unlockSavings = 0;

        while (!eventTimeQueue.isEmpty()) {
            final EventTime event = eventTimeQueue.pollFirst();
            if (event instanceof ComputerEvent) {
                final ComputerEvent computerEvent = (ComputerEvent) event;
                final Computer computer = computerEvent.computer;

                if (event.getEventType() == EventTypes.FREE) {
                    // Free up the computer as nobody is using it already
                    availableComputers.add(computer);
                }

                if (event.getEventType() == EventTypes.LOCK) {
                    // Check if the computer's expiry is the event type
                    if (event.getEventTime().equals(computer.getExpiry())) {
                        // Remove it from available computers
                        availableComputers.remove(computer);
                    }
                }
            }

            if (event instanceof ResearcherEvent) {
                if (event.getEventType() == EventTypes.ARRIVAL) {
                    final ResearcherEvent researcherEvent = (ResearcherEvent) event;
                    final Researcher researcher = researcherEvent.researcher;
                    final Integer computerLockTime = researcher.departureTime + inactivityLockMinutes;

                    Computer computer;
                    if (!availableComputers.isEmpty()) {
                        // Use unlocked computer
                        computer = availableComputers.pollFirst();
                        // Update its new locktime
                        if (computer != null) {
                            computer.setExpiry(computerLockTime);
                            // increment savings
                            unlockSavings++;
                        }
                    } else {
                        // Create a new computer for use
                        computer = new Computer(computerLockTime);
                    }
                    final EventTime computerFreeEvent = new ComputerEvent(researcher.departureTime, EventTypes.FREE, computer);
                    final EventTime computerLockEvent = new ComputerEvent(computerLockTime, EventTypes.LOCK, computer);

                    eventTimeQueue.add(computerFreeEvent);
                    eventTimeQueue.add(computerLockEvent);
                }
            }
        }

        return unlockSavings;
    }
}