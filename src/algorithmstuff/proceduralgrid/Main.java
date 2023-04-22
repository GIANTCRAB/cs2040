
public class Main {
    public static void main(String[] args) {
        int seedNumber = 52734566;
        int floorLevel = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        boolean[][] mapArray = new boolean[size][size];
        boolean[][][] connectedEdges = new boolean[size][size][4]; // 1st index is left, 2nd index is top, 3rd index is right, last index is bottom
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mapArray[i][j] = shouldHaveNode(seedNumber, floorLevel, size, i, j);
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolean hasNode = mapArray[i][j];
                if(hasNode) {
                    // check neighbours up, down, left, right
                    int leftX = i - 1;
                    int leftY = j;
                    int upX = i;
                    int upY = j - 1;
                    int rightX = i + 1;
                    int rightY = j;
                    int downX = i;
                    int downY = j + 1;

                    if(leftX >= 0) {
                        connectedEdges[i][j][0] = mapArray[leftX][leftY];
                    }
                    if(upY >= 0) {
                        connectedEdges[i][j][1] = mapArray[upX][upY];
                    }
                    if(rightX < size) {
                        connectedEdges[i][j][1] = mapArray[rightX][rightY];
                    }
                    if(downY < size) {
                        connectedEdges[i][j][1] = mapArray[downX][downY];
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(mapArray[i][j]) {
                    stringBuilder.append("X ");
                } else {
                    stringBuilder.append(". ");
                }
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    public static boolean shouldHaveNode(int seed, int floorLevel, int mapSize, int x, int y) {
        int xResult = seed % (x + 3);
        int yResult = seed % (y + 3);
        int floorResult = seed % floorLevel;
        int mapResult = seed % mapSize;

        int i = ((xResult + yResult) + (floorResult + mapResult)) % (x + y + 3);
        return i == 0;
    }
}
