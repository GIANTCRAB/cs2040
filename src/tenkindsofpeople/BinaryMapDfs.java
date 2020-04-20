package tenkindsofpeople;

import java.util.*;

/**
 * Using code from IslandsDfs
 *
 * @author WOO HUIREN ( A0202242B )
 */
class BinaryMapDfs {
    // Coordinates as key and group as value
    private final Map<Pair<Integer, Integer>, Integer> mapData;
    private final Deque<Pair<Integer, Integer>> mapStack = new ArrayDeque<>();
    private final Integer height;
    private final Integer width;
    private final char[][] map;
    // Counter used for grouping
    private int counter = 1;

    BinaryMapDfs(Integer height, Integer width, char[][] map) {
        this.height = height;
        this.width = width;
        final int total = width * height;
        this.map = map;
        this.mapData = new HashMap<>(total);
    }

    public String checkCoordinates(int r1, int c1, int r2, int c2) {
        final Pair<Integer, Integer> positionOneOne = new Pair<>(r1, c1);
        final Pair<Integer, Integer> positionOneTwo = new Pair<>(r2, c2);

        final Integer positionOneOneData = this.mapData.getOrDefault(positionOneOne, null);
        final Integer positionOneTwoData = this.mapData.getOrDefault(positionOneTwo, null);

        if (positionOneOne.equals(positionOneTwo)) {
            if (map[r1][c1] == '1') {
                return "decimal";
            } else {
                return "binary";
            }
        } else {
            if (positionOneOneData == null && positionOneTwoData == null) {
                // Do depth first search
                this.mapStack.add(positionOneOne);

                while (!this.mapStack.isEmpty()) {
                    final Pair<Integer, Integer> inspectCoords = this.mapStack.pop();
                    final Integer inspectX = inspectCoords.firstData;
                    final Integer inspectY = inspectCoords.secondData;
                    char currentValue = this.map[inspectX][inspectY];
                    final Pair<Integer, Integer> currentPositionOne = new Pair<>(inspectX, inspectY);

                    if (!this.mapData.containsKey(currentPositionOne)) {
                        // Not visited, set it as visited and set the visitation group
                        this.mapData.put(currentPositionOne, this.counter);

                        // Make sure to not re-visit places that has already been visited
                        if (inspectX > 0 && this.map[inspectX - 1][inspectY] == currentValue && !this.mapData.containsKey(new Pair<>((inspectX - 1), inspectY))) {
                            this.mapStack.add(new Pair<>((inspectX - 1), inspectY));
                        }

                        if (inspectX < this.height - 1 && this.map[inspectX + 1][inspectY] == currentValue && !this.mapData.containsKey(new Pair<>((inspectX + 1), inspectY))) {
                            this.mapStack.add(new Pair<>((inspectX + 1), inspectY));
                        }

                        if (inspectY > 0 && this.map[inspectX][inspectY - 1] == currentValue && !this.mapData.containsKey(new Pair<>(inspectX, (inspectY - 1)))) {
                            this.mapStack.add(new Pair<>(inspectX, (inspectY - 1)));
                        }

                        if (inspectY < this.width - 1 && this.map[inspectX][inspectY + 1] == currentValue && !this.mapData.containsKey(new Pair<>(inspectX, (inspectY + 1)))) {
                            this.mapStack.add(new Pair<>(inspectX, (inspectY + 1)));
                        }
                    }
                }

                // Increment mapData group counter
                this.counter++;

                if (this.mapData.containsKey(positionOneTwo)) {
                    if (this.map[r1][c1] == '1') {
                        return "decimal";
                    } else {
                        return "binary";
                    }
                }
            } else {
                if (this.map[r1][c1] == this.map[r2][c2]) {
                    if (positionOneOneData != null && positionOneOneData.equals(positionOneTwoData)) {
                        if (this.map[r1][c1] == '1') {
                            return "decimal";
                        } else {
                            return "binary";
                        }
                    }
                }

            }
        }

        return "neither";
    }
}
