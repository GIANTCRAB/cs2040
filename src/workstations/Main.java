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
            var inputInfo = new StringTokenizer(br.readLine());
            int numberOfResearchers = Integer.parseInt(inputInfo.nextToken());
            int inactivityLockMinutes = Integer.parseInt(inputInfo.nextToken());

            final Queue<EventTime> eventTimeQueue = makeQueue(br, numberOfResearchers);

            final int totalUnlockSavings = processQueue(eventTimeQueue, inactivityLockMinutes);

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                bw.write(String.valueOf(totalUnlockSavings));
                bw.flush();
            }
        }
    }

    public static Queue<EventTime> makeQueue(BufferedReader br, int numberOfResearchers) throws IOException {
        Queue<EventTime> eventTimeQueue = new PriorityQueue<>(new EventTimeComparator());

        for (int i = 0; i < numberOfResearchers; i++) {
            var researcherInfo = new StringTokenizer(br.readLine());
            var arrivalTime = Integer.parseInt(researcherInfo.nextToken());
            var departureTime = Integer.parseInt(researcherInfo.nextToken()) + arrivalTime;
            var researcher = new Researcher(arrivalTime, departureTime);

            var researcherArrivalEvent = new ResearcherEvent(arrivalTime, EventTypes.ARRIVAL, researcher);

            eventTimeQueue.add(researcherArrivalEvent);
        }

        return eventTimeQueue;
    }

    public static int processQueue(Queue<EventTime> eventTimeQueue, int inactivityLockMinutes) {
        TreeSet<Computer> availableComputers = new TreeSet<>(new ComputerComparator());
        int unlockSavings = 0;

        while (!eventTimeQueue.isEmpty()) {
            EventTime event = eventTimeQueue.poll();
            if (event instanceof ComputerEvent) {
                var computerEvent = (ComputerEvent) event;
                var computer = computerEvent.computer;

                if (event.getEventType() == EventTypes.FREE) {
                    var nextLockTime = computerEvent.eventTime + inactivityLockMinutes;

                    // Free up the computer as nobody is using it already
                    computer.setResearcherUsing(null);
                    computer.expiry = nextLockTime;
                    availableComputers.add(computer);

                    EventTime computerLockEvent = new ComputerEvent(nextLockTime, EventTypes.LOCK, computer);
                    eventTimeQueue.add(computerLockEvent);
                }

                if (event.getEventType() == EventTypes.LOCK) {
                    // Check if any researcher is using
                    if (computer.researcherUsing == null && event.getEventTime().equals(computer.expiry)) {
                        // Remove it from available computers
                        availableComputers.remove(computer);
                    }
                }
            }

            if (event instanceof ResearcherEvent) {
                if (event.getEventType() == EventTypes.ARRIVAL) {
                    ResearcherEvent researcherEvent = (ResearcherEvent) event;
                    Researcher researcher = researcherEvent.researcher;
                    int computerLockTime = researcher.departureTime + inactivityLockMinutes;

                    Computer computer;
                    if (!availableComputers.isEmpty()) {
                        // Use unlocked computer
                        computer = availableComputers.pollFirst();
                        // Update its new locktime
                        if (computer != null) {
                            computer.expiry = computerLockTime;
                            // Set researcher
                            computer.setResearcherUsing(researcher);
                            // increment savings
                            unlockSavings++;
                        }
                    } else {
                        // Create a new computer for use
                        computer = new Computer(researcherEvent.researcher, computerLockTime);
                    }
                    EventTime computerFreeEvent = new ComputerEvent(researcherEvent.researcher.departureTime, EventTypes.FREE, computer);

                    eventTimeQueue.add(computerFreeEvent);
                }
            }
        }

        return unlockSavings;
    }
}