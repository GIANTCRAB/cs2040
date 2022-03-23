package kattis.tenkindsofpeople;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final StringBuilder output = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            /*
              Similar to Islands depth first search. Use char to represent map data
             */
            final StringTokenizer heightAndWidth = new StringTokenizer(br.readLine());
            final int height = Integer.parseInt(heightAndWidth.nextToken(), 10);
            final int width = Integer.parseInt(heightAndWidth.nextToken(), 10);
            char[][] map = new char[height][width];

            for (int i = 0; i < height; i++) {
                String rowInfo = br.readLine();
                map[i] = rowInfo.toCharArray();
            }

            final BinaryMapDfs binaryMapDfs = new BinaryMapDfs(height, width, map);

            final int queryCount = Integer.parseInt(br.readLine());

            for (int i = 0; i < queryCount; i++) {
                final StringTokenizer r1c1r2c2 = new StringTokenizer(br.readLine());
                // Reduce all coords by 1 since they're one based and we are using zero based indexing
                final int r1 = Integer.parseInt(r1c1r2c2.nextToken(), 10) - 1;
                final int c1 = Integer.parseInt(r1c1r2c2.nextToken(), 10) - 1;
                final int r2 = Integer.parseInt(r1c1r2c2.nextToken(), 10) - 1;
                final int c2 = Integer.parseInt(r1c1r2c2.nextToken(), 10) - 1;

                output.append(binaryMapDfs.checkCoordinates(r1, c1, r2, c2));
                output.append("\n");
            }


            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                bw.write(output.toString());
                bw.flush();
            }

        }
    }
}