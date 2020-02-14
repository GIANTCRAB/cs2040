package homeassign1b;

import java.util.Comparator;

public class RelayRunnerSecondLegComparator implements Comparator<RelayRunner> {
    @Override
    public int compare(RelayRunner o1, RelayRunner o2) {
        return o1.getSecondLegTime().compareTo(o2.getSecondLegTime());
    }
}
