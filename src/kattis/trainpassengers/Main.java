package kattis.trainpassengers;

import java.util.Scanner;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {

    public static void main(String[] args) {
        // Read the inputs
        final Scanner sc = new Scanner(System.in);

        final int maxCapacity = sc.nextInt();
        final int numberOfStations = sc.nextInt();
        int currentCapacity = 0;
        final int endCapacity = 0;
        boolean isPossible = true;

        for (int i = 0; i < numberOfStations; i++) {
            // number of people left train
            currentCapacity -= sc.nextInt();
            // number of people enter train
            final int peopleEnteringTrain = sc.nextInt();
            currentCapacity += peopleEnteringTrain;
            // number of people stay at station
            final int leftoverAtStation = sc.nextInt();

            if (currentCapacity < 0 || currentCapacity > maxCapacity) {
                // Capacity exceeded or below 0
                isPossible = false;
                break;
            }

            // Are there leftover despite having smaller than max capacity?
            if (currentCapacity < maxCapacity && leftoverAtStation > 0) {
                isPossible = false;
                break;
            }

            // Last station check?
            if (i + 1 == numberOfStations && (leftoverAtStation > 0 || peopleEnteringTrain > 0)) {
                isPossible = false;
                break;
            }
        }

        if (isPossible && currentCapacity != endCapacity) {
            // not the same as end capacity
            isPossible = false;
        }

        if (isPossible) {
            System.out.println("possible");
        } else {
            System.out.println("impossible");
        }
    }
}
