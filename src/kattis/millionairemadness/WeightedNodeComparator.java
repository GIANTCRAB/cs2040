package kattis.millionairemadness;

import java.util.Comparator;

/**
 * @author WOO HUIREN ( A0202242B )
 */
class WeightedNodeComparator<T> implements Comparator<WeightedNode<T>> {
    @Override
    public int compare(WeightedNode<T> o1, WeightedNode<T> o2) {
        return o1.getCost().compareTo(o2.getCost());
    }
}
