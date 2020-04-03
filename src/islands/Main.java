package islands;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final StringTokenizer heightAndWidth = new StringTokenizer(br.readLine());
            final int height = Integer.parseInt(heightAndWidth.nextToken(), 10);
            final int width = Integer.parseInt(heightAndWidth.nextToken(), 10);
            char[][] map = new char[height][width];

            for (int i = 0; i < height; i++) {
                String rowInfo = br.readLine();
                map[i]= rowInfo.toCharArray();
            }

            final IslandsDfs islandsDfs = new IslandsDfs(height, width, map);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    islandsDfs.checkCoordinates(i, j);
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                bw.write(String.valueOf(islandsDfs.getCount()));
                bw.flush();
            }

        }
    }
}