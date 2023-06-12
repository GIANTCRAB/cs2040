package algorithmstuff.proceduralgrid;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int seedNumber = 52734566;
        int floorLevel = Integer.parseInt(args[0]);

        final MapGenerator mapGenerator = new MapGenerator(seedNumber, floorLevel);
        mapGenerator.doMapping();
        mapGenerator.printMapping();
    }

    private static class MapGenerator {
        private final int seedNumber;
        private final int floorLevel;
        private final int maxDepth;
        private final Map<Pair<Integer, Integer>, Node> mapData;
        private final Random generator;
        private int smallestX = 0;
        private int smallestY = 0;
        private int largestX = 0;
        private int largestY = 0;

        public MapGenerator(int seedNumber, int floorLevel) {
            this.seedNumber = seedNumber;
            this.floorLevel = floorLevel;
            this.maxDepth = this.floorLevel + 2;
            this.mapData = new HashMap<>();
            this.generator = new Random(this.seedNumber);
        }

        public void doMapping() {
            final Pair<Integer, Integer> startingCoordinate = new Pair<>(0, 0);
            final Node startingNode = this.createNewNode(startingCoordinate, 0);

            final Stack<Node> nodeStack = new Stack<>();
            nodeStack.push(startingNode);

            while(!nodeStack.isEmpty()) {
                final Node currentNode = nodeStack.pop();
                final int currentDepth = currentNode.getDepth();
                if(currentDepth < this.maxDepth) {
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if(getRelativeCoordinateValidity(i, j)) {
                                final Pair<Integer, Integer> targetCoordinate = new Pair<>(currentNode.getX() + i, currentNode.getY() + j);
                                if(!this.mapData.containsKey(targetCoordinate)) {
                                    if(this.generator.nextBoolean()) {
                                        final Node newNode = this.createNewNode(targetCoordinate, currentDepth + 1);
                                        nodeStack.push(newNode);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        public void printMapping() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = this.smallestX; x <= this.largestX; x++) {
                for (int y = this.smallestY; y <= this.largestY; y++) {
                    final Pair<Integer, Integer> targetCoordinate = new Pair<>(x, y);
                    if(this.mapData.containsKey(targetCoordinate)) {
                        stringBuilder.append("X ");
                    } else {
                        stringBuilder.append(". ");
                    }
                }
                stringBuilder.append("\n");
            }
            System.out.println(stringBuilder);
        }

        private Node createNewNode(Pair<Integer, Integer> targetCoordinate, int depth) {
            if(targetCoordinate.firstData < this.smallestX) {
                this.smallestX = targetCoordinate.firstData;
            }
            if(targetCoordinate.secondData < this.smallestY) {
                this.smallestY = targetCoordinate.secondData;
            }
            if(targetCoordinate.firstData > this.largestX) {
                this.largestX = targetCoordinate.firstData;
            }
            if(targetCoordinate.secondData > this.largestY) {
                this.largestY = targetCoordinate.secondData;
            }

            final Node newNode = new Node(targetCoordinate.firstData, targetCoordinate.secondData, depth);
            final Set<Node> newNodeNeighbours = calculateAllNeighbours(newNode);
            newNode.updateNeighbours(newNodeNeighbours);
            for (Node newNodeNeighbour: newNodeNeighbours) {
                newNodeNeighbour.addNeighbour(newNode);
            }
            this.mapData.put(targetCoordinate, newNode);
            return newNode;
        }

        private Set<Node> calculateAllNeighbours(Node subjectNode) {
            final Set<Node> neighbours = new HashSet<>();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (getRelativeCoordinateValidity(i, j)) {
                        final Pair<Integer, Integer> targetCoordinate = new Pair<>(subjectNode.getX() + i, subjectNode.getY() + j);
                        if (this.mapData.containsKey(targetCoordinate)) {
                            neighbours.add(this.mapData.get(targetCoordinate));
                        }
                    }
                }
            }
            return neighbours;
        }

        private static boolean getRelativeCoordinateValidity(int x, int y) {
            final boolean isNotCenter = !(x == 0 && y == 0);
            final boolean isNotTopLeft = !(x == -1 && y == -1);
            final boolean isNotBottomLeft = !(x == -1 && y == 1);
            final boolean isNotTopRight = !(x == 1 && y == -1);
            final boolean isNotBottomRight = !(x == 1 && y == 1);
            return (isNotCenter && isNotTopLeft && isNotBottomLeft && isNotTopRight && isNotBottomRight);
        }
    }

    private static class Node {
        private final int x;
        private final int y;
        private final int depth;
        private final Set<Node> neighbours;

        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.neighbours = new HashSet<>();
        }

        public void updateNeighbours(Set<Node> allNodes) {
            this.neighbours.clear();
            this.neighbours.addAll(allNodes);
        }
        public void addNeighbour(Node neighbourNode) {
            this.neighbours.add(neighbourNode);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getDepth() {
            return depth;
        }

        public Set<Node> getNeighbours() {
            return neighbours;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;

            Node node = (Node) o;

            if (this.getX() != node.getX()) return false;
            return this.getY() == node.getY();
        }

        @Override
        public int hashCode() {
            int result = this.getX();
            result = 31 * result + this.getY();
            return result;
        }
    }

    private static class Pair<T, E> {
        final T firstData;
        final E secondData;

        public Pair(T firstData, E secondData) {
            this.firstData = firstData;
            this.secondData = secondData;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(firstData, pair.firstData) && Objects.equals(secondData, pair.secondData);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstData, secondData);
        }
    }
}
