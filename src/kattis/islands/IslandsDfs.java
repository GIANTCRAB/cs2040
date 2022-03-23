package kattis.islands;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class IslandsDfs {
    /**
     * The problem seems like a stack problem?
     */
    private final Set<Pair<Integer, Integer>> visited = new HashSet<>();
    private final Integer height;
    private final Integer width;
    private final char[][] map;
    private int counter = 0;

    IslandsDfs(Integer height, Integer width, char[][] map) {
        this.height = height;
        this.width = width;
        this.map = map;
    }

    public void checkCoordinates(int i, int j) {
        final char data = this.map[i][j];
        final Pair<Integer, Integer> coordsData = new Pair<>(i, j);
        if (data == 'L' && !this.visited.contains(coordsData)) {
            this.counter++;

            Deque<Pair<Integer, Integer>> stack = new ArrayDeque<>();
            stack.add(coordsData);

            while (!stack.isEmpty()) {
                Pair<Integer, Integer> inspectCoords = stack.pop();
                if (!this.visited.contains(inspectCoords)) {
                    // Add the coords to list of inspected data
                    this.visited.add(inspectCoords);

                    // Check height related stuff
                    if (inspectCoords.firstData - 1 >= 0 && this.map[inspectCoords.firstData - 1][inspectCoords.secondData] != 'W') {
                        final Pair<Integer, Integer> newInspectionCoords = new Pair<>(inspectCoords.firstData - 1, inspectCoords.secondData);
                        stack.add(newInspectionCoords);
                    }
                    if (inspectCoords.firstData + 1 < this.height && this.map[inspectCoords.firstData + 1][inspectCoords.secondData] != 'W') {
                        final Pair<Integer, Integer> newInspectionCoords = new Pair<>(inspectCoords.firstData + 1, inspectCoords.secondData);
                        stack.add(newInspectionCoords);
                    }
                    // Check width related data
                    if (inspectCoords.secondData - 1 >= 0 && this.map[inspectCoords.firstData][inspectCoords.secondData - 1] != 'W') {
                        final Pair<Integer, Integer> newInspectionCoords = new Pair<>(inspectCoords.firstData, inspectCoords.secondData - 1);
                        stack.add(newInspectionCoords);
                    }
                    if (inspectCoords.secondData + 1 < this.width && this.map[inspectCoords.firstData][inspectCoords.secondData + 1] != 'W') {
                        final Pair<Integer, Integer> newInspectionCoords = new Pair<>(inspectCoords.firstData, inspectCoords.secondData + 1);
                        stack.add(newInspectionCoords);
                    }
                }
            }
        }
    }

    public int getCount() {
        return this.counter;
    }
}
