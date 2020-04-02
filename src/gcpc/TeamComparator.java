package gcpc;

import java.util.Comparator;

public class TeamComparator implements Comparator<Team> {
    @Override
    public int compare(Team o1, Team o2) {
        return o1.getPenalty().compareTo(o2.getPenalty());
    }
}
