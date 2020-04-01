package ladice;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final StringBuilder output = new StringBuilder();
        // We need to init the UFDS array with some int value...
        // When it start failing test case, increase the number
        final int BIG_INT_VALUE = 524288;
        final UnionFind ufds = new UnionFind(BIG_INT_VALUE);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final StringTokenizer infoLine = new StringTokenizer(br.readLine());
            // We only need number of items, ignore drawer count
            // Not sure why we are given number of drawers since we're going to use UFDS for merging?
            final int numberOfItems = Integer.parseInt(infoLine.nextToken());

            for (int i = 0; i < numberOfItems; i++) {
                final StringTokenizer itemInfo = new StringTokenizer(br.readLine());
                final int pairOfDrawers = Integer.parseInt(itemInfo.nextToken());
                final int item = Integer.parseInt(itemInfo.nextToken());

                ufds.unionSet(pairOfDrawers, item);

                if (ufds.incrementAndCheckUsed(pairOfDrawers)) {
                    output.append("LADICA");
                } else {
                    output.append("SMECE");
                }
                output.append("\n");
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
            bw.write(output.toString());

            bw.flush();
        }
    }
}