package workstations;

import java.util.Comparator;

public class ResearcherEventComparator implements Comparator<ResearcherEvent> {
    @Override
    public int compare(ResearcherEvent o1, ResearcherEvent o2) {
        return o1.eventTime.compareTo(o2.eventTime);
    }
}
