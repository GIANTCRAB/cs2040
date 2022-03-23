package kattis.weakvertices;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {
        boolean keepLooping = true;
        final StringBuilder output = new StringBuilder();


        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (keepLooping) {
                final int vertexCount = Integer.parseInt(br.readLine());
                if (vertexCount == -1) {
                    keepLooping = false;
                } else {
                    final int[][] vertices = new int[vertexCount][vertexCount];

                    for (int i = 0; i < vertexCount; i++) {
                        StringTokenizer rowInfo = new StringTokenizer(br.readLine());

                        // Read each individual column in the row
                        for (int j = 0; j < vertexCount; j++) {
                            final int value = Integer.parseInt(rowInfo.nextToken());

                            vertices[i][j] = value;
                        }
                    }

                    for (int i = 0; i < vertexCount; i++) {
                        boolean isWeak = true;

                        // Check all its connections for the respective vertex
                        for (int j = 0; j < vertexCount; j++) {
                            // Check the connections for the respective vertex's connection
                            for (int k = 0; k < vertexCount; k++) {
                                if (vertices[i][k] == 1 && vertices[i][j] == 1 && vertices[j][k] == 1 && i != k && i != j && j != k) {
                                    isWeak = false;
                                    break;
                                }
                            }
                        }

                        if (isWeak) {
                            output.append(i).append(" ");
                        }
                    }
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