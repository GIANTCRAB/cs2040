package assignment4;

import java.util.*;

/**
 * @author WOO HUIREN ( A0202242B )
 */
public class Main {

    public static void main(String[] args) {
        // Read the inputs
        final Scanner sc = new Scanner(System.in);

        final int syllableCount = sc.nextInt();
        final int playerCount = sc.nextInt();

        final HandProcessor handProcessor = new HandProcessor(playerCount, syllableCount);
        System.out.print(handProcessor.runSimulation());
    }
}
