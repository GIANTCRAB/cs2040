package codility.demo;

import java.util.Arrays;

public class solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        int smallestInt = 1;

        final int[] sortedValues = Arrays.stream(A).sorted().toArray();
        for (int a : sortedValues) {
            if (a == smallestInt) {
                smallestInt++;
            }
        }

        return smallestInt;
    }
}
