package kattis.judgingmoose;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int left = sc.nextInt();
        final int right = sc.nextInt();
        final int total = left + right;
        final int max = Math.max(left, right);
        final int maxTotal = max * 2;

        // Check if it is a moose
        //  || Math.abs(left - right) > 1
        if (total == 0) {
            System.out.print("Not a moose");
        } else {

            if (total % 2 == 0) {
                // Even
                System.out.printf("Even %d", maxTotal);
            } else {
                // If it is odd, +1
                System.out.printf("Odd %d", maxTotal);
            }
        }
    }
}
