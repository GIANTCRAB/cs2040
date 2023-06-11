
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
            final Node startingNode = this.createNewNode(startingCoordinate);
            this.diveNode(startingNode, 0);
        }

        public void printMapping() {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = this.smallestX; x <= largestX; x++) {
                for (int y = this.smallestY; y <= largestY; y++) {
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

        private void diveNode(Node subjectNode, int currentDepth) {
            if(currentDepth >= maxDepth) {
                return;
            }

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    final boolean isNotCenter = !(i == 0 && j == 0);
                    final boolean isNotTopLeft = !(i == -1 && j == -1);
                    final boolean isNotBottomLeft = !(i == -1 && j == 1);
                    final boolean isNotTopRight = !(i == 1 && j == -1);
                    final boolean isNotBottomRight = !(i == 1 && j == 1);
                    if(isNotCenter && isNotTopLeft && isNotBottomLeft && isNotTopRight && isNotBottomRight) {
                        final Pair<Integer, Integer> targetCoordinate = new Pair<>(subjectNode.getX() + i, subjectNode.getY() + j);
                        if(!this.mapData.containsKey(targetCoordinate)) {
                            if(this.generator.nextBoolean()) {
                                final Node newNode = this.createNewNode(targetCoordinate);
                                diveNode(newNode, currentDepth + 1);
                            }
                        }
                    }
                }
            }
        }

        private Node createNewNode(Pair<Integer, Integer> targetCoordinate) {
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

            final Node newNode = new Node(targetCoordinate.firstData, targetCoordinate.secondData);
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
                    final boolean isNotCenter = !(i == 0 && j == 0);
                    final boolean isNotTopLeft = !(i == -1 && j == -1);
                    final boolean isNotBottomLeft = !(i == -1 && j == 1);
                    final boolean isNotTopRight = !(i == 1 && j == -1);
                    final boolean isNotBottomRight = !(i == 1 && j == 1);
                    if (isNotCenter && isNotTopLeft && isNotBottomLeft && isNotTopRight && isNotBottomRight) {
                        final Pair<Integer, Integer> targetCoordinate = new Pair<>(subjectNode.getX() + i, subjectNode.getY() + j);
                        if (this.mapData.containsKey(targetCoordinate)) {
                            neighbours.add(this.mapData.get(targetCoordinate));
                        }
                    }
                }
            }
            return neighbours;
        }
    }

    private static class Node {
        private final int x;
        private final int y;
        private final Set<Node> neighbours;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.neighbours = new HashSet<>();
        }

        // Do a re-calculation to add new neighbours in
        public void updateNeighbours(Set<Node> allNodes) {

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
