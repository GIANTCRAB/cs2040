package workstations;

import java.util.Comparator;

public class ComputerComparator implements Comparator<Computer> {
    @Override
    public int compare(Computer o1, Computer o2) {
        return o1.getExpiry().compareTo(o2.getExpiry());
    }
}
