package leetcode.mergesortedarray;

import java.util.Arrays;

//TODO: make it faster
public class Solution {
    public void merge(int[] leftArray, int leftArrayEndPoint, int[] rightArray, int rightArrayLength) {
        if (rightArrayLength == 0) {
            return;
        }

        final int leftArrayLength = leftArray.length;

        int leftArrayIndex = 0;
        int highestLeftIndex = leftArrayEndPoint;
        int rightArrayIndex = 0;

        while (true) {
            if (leftArrayIndex >= highestLeftIndex) {
                // swap and increment right array index
                final int leftArrayValue = leftArray[leftArrayIndex];
                leftArray[leftArrayIndex] = rightArray[rightArrayIndex];
                rightArray[rightArrayIndex] = leftArrayValue;
                rightArrayIndex++;
                leftArrayIndex = 0;
                highestLeftIndex++;
                if (rightArrayIndex == rightArrayLength) {
                    break;
                }
            }
            // When left side is larger, swap with the right side value
            if (leftArray[leftArrayIndex] > rightArray[rightArrayIndex]) {
                // swap and sort right side array
                final int leftArrayValue = leftArray[leftArrayIndex];
                leftArray[leftArrayIndex] = rightArray[rightArrayIndex];
                rightArray[rightArrayIndex] = leftArrayValue;
                leftArrayIndex = 0;
                rightArray = sortArray(rightArray);
            } else {
                leftArrayIndex++;
                if (leftArrayIndex == leftArrayLength) {
                    break;
                }
            }
        }
    }

    private int[] sortArray(int[] givenArray) {
        return Arrays.stream(givenArray).sorted().toArray();
    }
}
