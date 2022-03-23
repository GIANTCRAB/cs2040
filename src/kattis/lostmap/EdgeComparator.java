package kattis.lostmap;

import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge> {
    @Override
    public int compare(Edge firstEdge, Edge secondEdge) {
        return firstEdge.getDistance().compareTo(secondEdge.getDistance());
    }
}
