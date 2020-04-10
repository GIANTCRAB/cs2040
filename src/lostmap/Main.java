package lostmap;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final int n = Integer.parseInt(br.readLine());
            final MinimumSpanningTree kruskalMst = new MinimumSpanningTree(n);

            for (int i = 0; i < n; i++) {
                final StringTokenizer infoLine = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    final int vertexDistance = Integer.parseInt(infoLine.nextToken());
                    if (i > j) {
                        kruskalMst.addEdge(new Edge(i, j, vertexDistance));
                    }
                }
            }

            final String output = kruskalMst.runKruskal().toString();

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                bw.write(output);
                bw.flush();
            }
        }
    }
}