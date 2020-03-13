package workstations;

import java.util.Comparator;

public class EventTimeComparator implements Comparator<EventTime> {
    @Override
    public int compare(EventTime o1, EventTime o2) {
        final int firstComparison = o1.getEventStart().compareTo(o2.getEventStart());
        if (firstComparison == 0) {
            final int secondComparison = o1.getEventEnd().compareTo(o2.getEventEnd());
            if (secondComparison == 0) {
                // Free'ing should take most priority
                if (o1.getEventType() == EventTypes.FREE) {
                    // Don't shift since both are priority
                    if (o2.getEventType() == EventTypes.FREE) {
                        return 0;
                    }

                    return -1;
                }
            }

            return secondComparison;
        }
        return firstComparison;
    }
}
