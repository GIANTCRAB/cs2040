package millionairemadness;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author WOO HUIREN ( A0202242B )
 */
class MillionaireSearch {
    private final int[][] map;

    MillionaireSearch(int[][] map) {
        this.map = map;
    }

    int search(int endPositionY, int endPositionX) {
        // Using priority queue as BFS
        final Queue<WeightedNode<Integer>> priorityQueue = new PriorityQueue<>(new WeightedNodeComparator<>());
        // Initialize the whole map as unvisited first
        Boolean[] visitedMap = new Boolean[endPositionY * endPositionX];
        Arrays.fill(visitedMap, Boolean.FALSE);

        // Start from position 0
        priorityQueue.add(new WeightedNode<>(0, 0, 0));

        while (!priorityQueue.isEmpty()) {
            final WeightedNode<Integer> currentNode = priorityQueue.poll();

            // Instead of using Pair, use a faster way to index their positions
            int positionOne = currentNode.getPosY() * endPositionX + currentNode.getPosX();

            // Check if visited
            if (!visitedMap[positionOne]) {
                // Goal check
                if (currentNode.getPosY() == (endPositionY - 1) && currentNode.getPosX() == (endPositionX - 1)) {
                    // We reached our goal
                    return currentNode.getCost();
                } else {
                    // Mark current node as visited
                    visitedMap[positionOne] = true;

                    // Make sure the newly added places are not visited as well
                    if (currentNode.getPosY() > 0 && !visitedMap[(currentNode.getPosY() - 1) * endPositionX + currentNode.getPosX()]) {
                        final int cost = this.map[currentNode.getPosY() - 1][currentNode.getPosX()] - this.map[currentNode.getPosY()][currentNode.getPosX()];
                        priorityQueue.add(new WeightedNode<>(currentNode.getPosY() - 1, currentNode.getPosX(), cost));
                    }

                    if (currentNode.getPosY() < endPositionY - 1 && !visitedMap[(currentNode.getPosY() + 1) * endPositionX + currentNode.getPosX()]) {
                        final int cost = this.map[currentNode.getPosY() + 1][currentNode.getPosX()] - this.map[currentNode.getPosY()][currentNode.getPosX()];
                        priorityQueue.add(new WeightedNode<>(currentNode.getPosY() + 1, currentNode.getPosX(), cost));
                    }

                    if (currentNode.getPosX() > 0 && !visitedMap[currentNode.getPosY() * endPositionX + (currentNode.getPosX() - 1)]) {
                        final int cost = this.map[currentNode.getPosY()][currentNode.getPosX() - 1] - this.map[currentNode.getPosY()][currentNode.getPosX()];
                        priorityQueue.add(new WeightedNode<>(currentNode.getPosY(), currentNode.getPosX() - 1, cost));
                    }

                    if (currentNode.getPosX() < endPositionX - 1 && !visitedMap[currentNode.getPosY() * endPositionX + (currentNode.getPosX() + 1)]) {
                        final int cost = this.map[currentNode.getPosY()][currentNode.getPosX() + 1] - this.map[currentNode.getPosY()][currentNode.getPosX()];
                        priorityQueue.add(new WeightedNode<>(currentNode.getPosY(), currentNode.getPosX() + 1, cost));
                    }
                }
            }
        }

        // When no path, return infinity (or we use negative 1 as a bit indicator)
        return -1;
    }
}
