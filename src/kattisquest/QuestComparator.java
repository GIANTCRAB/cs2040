package kattisquest;

import java.util.Comparator;

public class QuestComparator implements Comparator<Quest> {
    @Override
    public int compare(Quest o1, Quest o2) {
        final int firstComparison = o1.getEnergy().compareTo(o2.getEnergy());

        if (firstComparison == 0) {
            return o1.getGold().compareTo(o2.getGold());
        }

        return firstComparison;
    }
}
