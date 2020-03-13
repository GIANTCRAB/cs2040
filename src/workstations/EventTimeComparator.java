package workstations;

import java.util.Comparator;

public class EventTimeComparator implements Comparator<EventTime> {
    @Override
    public int compare(EventTime o1, EventTime o2) {
        final int firstComparison = o1.getEventStart().compareTo(o2.getEventStart());
        if (firstComparison == 0) {
            return o1.getEventEnd().compareTo(o2.getEventEnd());
        }
        return firstComparison;
    }
}
