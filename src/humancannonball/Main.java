package humancannonball;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final List<Pair<Double, Double>> points = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final StringTokenizer startXY = new StringTokenizer(br.readLine());
            final double startX = Double.parseDouble(startXY.nextToken());
            final double startY = Double.parseDouble(startXY.nextToken());
            points.add(new Pair<>(startX, startY));

            final StringTokenizer endXY = new StringTokenizer(br.readLine());
            final double endX = Double.parseDouble(endXY.nextToken());
            final double endY = Double.parseDouble(endXY.nextToken());

            final int numberOfCannons = Integer.parseInt(br.readLine());
            // Why plus 2? Because it needs to account for start and end cannons, which number of cannons has excluded.
            final int totalCannonCount = numberOfCannons + 2;

            final double[][] graph = new double[totalCannonCount][totalCannonCount];

            for (int i = 0; i < numberOfCannons; i++) {
                final StringTokenizer cannonXY = new StringTokenizer(br.readLine());
                final double cannonX = Double.parseDouble(cannonXY.nextToken());
                final double cannonY = Double.parseDouble(cannonXY.nextToken());

                points.add(new Pair<>(cannonX, cannonY));
            }

            points.add(new Pair<>(endX, endY));

            for (int i = 0; i < totalCannonCount; i++) {
                for (int j = i; j < totalCannonCount; j++) {
                    if (i == j) {
                        graph[i][j] = 0;
                    } else {
                        final Pair<Double, Double> pointI = points.get(i);
                        final double pointIX = pointI.firstData;
                        final double pointIY = pointI.secondData;
                        final Pair<Double, Double> pointJ = points.get(j);
                        final double pointJX = pointJ.firstData;
                        final double pointJY = pointJ.secondData;

                        // Pytogras theorem
                        final double calculatedDistance = Math.sqrt(Math.pow(pointIX - pointJX, 2) + Math.pow(pointIY - pointJY, 2));
                        if (i == 0 || j == 0) {
                            final double finalDist = calculatedDistance / 5.0;
                            graph[i][j] = finalDist;
                            graph[j][i] = finalDist;
                        } else {
                            double cost;
                            if (calculatedDistance >= 50) {
                                cost = 2 + (calculatedDistance - 50) / 5.0;
                            } else {
                                if (calculatedDistance >= 30) {
                                    cost = 2 + (50 - calculatedDistance) / 5.0;
                                } else {
                                    cost = calculatedDistance / 5.0;
                                }
                            }

                            graph[i][j] = cost;
                            graph[j][i] = cost;
                        }
                    }
                }
            }

            DijkstraSSSP dijkstraSSSP = new DijkstraSSSP(graph, totalCannonCount);

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileDescriptor.out), StandardCharsets.US_ASCII), 512)) {
                bw.write(String.valueOf(dijkstraSSSP.search(0)));
                bw.flush();
            }

        }
    }
}