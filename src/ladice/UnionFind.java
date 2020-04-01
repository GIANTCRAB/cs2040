package ladice;

// Barebones Union-Find Disjoint Sets implementation,
// using both path compression and union by rank heuristics

/**
 * Adapted from Dr Chong's version
 *
 * @author WOO HUIREN ( A0202242B )
 */

class UnionFind {
    public int[] p;
    public int[] rank;
    /**
     * Introduce two new arrays - used and setSize
     * used is to store info related to the drawer has been used
     */
    public int[] used;
    public int[] setSize;

    public UnionFind(int N) {
        p = new int[N];
        rank = new int[N];
        used = new int[N];
        setSize = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
            used[i] = 0;
            setSize[i] = 1; // init as 1 because when a new set gets stored, its size will be 1
        }
    }

    public int findSet(int i) {
        if (p[i] == i) {
            return i;
        } else {
            p[i] = findSet(p[i]);
            return p[i];
        }
    }

    public void unionSet(int i, int j) {
        int x = findSet(i), y = findSet(j);
        if (x != y) {
            // rank is used to keep the tree short
            if (rank[x] > rank[y]) {
                p[y] = x;
                // Combine the size when merging
                setSize[x] += setSize[y];
                used[x] += used[y];
            } else {
                p[x] = y;
                // Combine the size when merging
                setSize[y] += setSize[x];
                used[y] += used[x];
                if (rank[x] == rank[y]) {
                    rank[y] = rank[y] + 1;
                }
            }
        }
    }

    /**
     * Check if can be stored by looking at the total size (after merging with other sets)
     *
     * @param i
     * @return
     */
    public Boolean incrementAndCheckUsed(int i) {
        final int foundSet = findSet(i);
        if (used[foundSet] < setSize[foundSet]) {
            // Can be stored, increment number of uses
            used[foundSet]++;
            return true;
        } else {
            // Thrown away
            return false;
        }
    }
}