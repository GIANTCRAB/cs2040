package humancannonball;

import java.util.Arrays;

public class DijkstraSSSP {
    private double[][] graph;
    private int nSize;

    public DijkstraSSSP(double[][] graph, int nSize) {
        this.graph = graph;
        this.nSize = nSize;
    }

    double search(int src) {
        double[] distances = new double[nSize];
        Arrays.fill(distances, Integer.MAX_VALUE);
        // Initialize the whole map as unvisited first
        Boolean[] visitedMap = new Boolean[this.nSize];
        Arrays.fill(visitedMap, Boolean.FALSE);

        distances[src] = 0.0;

        for (int i = 0; i < this.nSize; i++) {
            final int minIndex = getMinDistance(distances, visitedMap);

            visitedMap[minIndex] = true;

            for (int j = 0; j < this.nSize; j++) {
                if (this.graph[minIndex][j] > 0 && !visitedMap[j] && distances[j] > (distances[minIndex] + this.graph[minIndex][j])) {
                    distances[j] = distances[minIndex] + this.graph[minIndex][j];
                }
            }
        }

        return distances[nSize - 1];
    }

    private int getMinDistance(double[] distances, Boolean[] visitedMap) {
        double min = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < this.nSize; i++) {
            if (!visitedMap[i] && distances[i] < min) {
                min = distances[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}
