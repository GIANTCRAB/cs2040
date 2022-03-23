package kattis.millionairemadness;

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
            int[][] map = new int[height][width];

            for (int i = 0; i < height; i++) {
                final StringTokenizer row = new StringTokenizer(br.readLine());
                for (int j = 0; j < width; j++) {
                    map[i][j] = Integer.parseInt(row.nextToken(), 10);
                }
            }

            final MillionaireSearch millionaireSearch = new MillionaireSearch(map);

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                bw.write(String.valueOf(millionaireSearch.search(height, width)));
                bw.flush();
            }

        }
    }
}