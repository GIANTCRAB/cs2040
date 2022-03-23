package kattis.lostmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Kruskal’s Minimum Spanning Tree
 *
 * @author WOO HUIREN ( A0202242B )
 */
public class MinimumSpanningTree {
    private final List<Edge> edges;
    private final int vertexCount;

    MinimumSpanningTree(int vertexCount) {
        this.vertexCount = vertexCount;
        this.edges = new ArrayList<>(vertexCount - 1);
    }

    /**
     * Adds an undirected edge to MST
     *
     * @param e
     */
    public void addEdge(Edge e) {
        this.edges.add(e);
    }

    /**
     * Find root
     *
     * @param subsets
     * @param i
     * @return
     */
    Integer find(Subset[] subsets, Integer i) {
        if (!subsets[i].parent.equals(i)) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }

        return subsets[i].parent;
    }

    /**
     * Union by rank
     *
     * @param subsets
     * @param x
     * @param y
     */
    void union(Subset[] subsets, Integer x, Integer y) {
        Integer xRoot = find(subsets, x);
        Integer yRoot = find(subsets, y);

        if (subsets[xRoot].rank < subsets[yRoot].rank) {
            subsets[xRoot].parent = yRoot;
        } else if (subsets[xRoot].rank > subsets[yRoot].rank) {
            subsets[yRoot].parent = xRoot;
        } else {
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        }
    }

    /**
     * A modified version of kruskal.
     * Instead of returning the complete kruskal, we return a String Builder with the data on what has been union joined
     *
     * @return
     */
    StringBuilder runKruskal() {
        final StringBuilder output = new StringBuilder();

        // Sort the edges by their distance
        this.edges.sort(new EdgeComparator());

        // Allocate subsets array
        final Subset[] subsets = new Subset[this.vertexCount];
        for (int i = 0; i < this.vertexCount; i++) {
            subsets[i] = new Subset(i, 0);
        }

        this.edges.forEach(nextEdge -> {
            // Check their parents (udfs)
            final Integer x = find(subsets, nextEdge.getV());
            final Integer y = find(subsets, nextEdge.getW());

            // Detect for cycle, if they have same parents, discard.
            if (!x.equals(y)) {
                // They do not have same parents, union them.
                output.append(nextEdge.getW() + 1)
                        .append(" ")
                        .append((nextEdge.getV() + 1))
                        .append("\n");
                this.union(subsets, x, y);
            }
        });

        return output;
    }
}
