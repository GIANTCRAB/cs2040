package workstations;

import java.util.Comparator;

public class ResearcherEventComparator implements Comparator<ResearcherEvent> {
    @Override
    public int compare(ResearcherEvent o1, ResearcherEvent o2) {
        var firstComparison = o1.eventTime.compareTo(o2.eventTime);
        if (firstComparison == 0) {
            // Departure should take most priority
            if (o1.eventType == EventTypes.DEPARTURE && o2.eventType != EventTypes.DEPARTURE) {
                return -1;
            }
            // Arrival should take priority over LOCK
            if (o1.eventType == EventTypes.ARRIVAL && o2.eventType == EventTypes.LOCK) {
                return -1;
            }
        }
        return firstComparison;
    }
}
