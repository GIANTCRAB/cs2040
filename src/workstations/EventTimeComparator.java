package workstations;

import java.util.Comparator;

public class EventTimeComparator implements Comparator<EventTime> {
    @Override
    public int compare(EventTime o1, EventTime o2) {
        int firstComparison = o1.getEventTime().compareTo(o2.getEventTime());
        if (firstComparison == 0) {
            // Free'ing should take most priority
            if (o1.getEventType() == EventTypes.FREE) {
                // Don't shift since both are priority
                if (o2.getEventType() == EventTypes.FREE) {
                    return 0;
                }

                return -1;
            }
            if (o2.getEventType() == EventTypes.FREE) {
                return 1;
            }

            // Arrival should take priority over LOCK
            if (o1.getEventType() == EventTypes.ARRIVAL) {
                if (o2.getEventType() == EventTypes.LOCK) {
                    return -1;
                }
            }

            if (o1.getEventType() == EventTypes.LOCK) {
                if (o2.getEventType() == EventTypes.LOCK) {
                    return 0;
                }
                return 1;
            }
        }
        return firstComparison;
    }
}
