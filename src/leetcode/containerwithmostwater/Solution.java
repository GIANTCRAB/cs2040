package leetcode.containerwithmostwater;

// I think of scanning from one point to another point
// In a way, it is kind of a basic sliding window. you never need to check the points you have passed-by.
// my right index will always be the last point. and thereafter, it should continuously shrink
// we keep checking the amount remaining as our right index decrements
// as for optimisation, i think we can stop shrinking when:
// the current biggest beats the largest possible water amount (height of the left index * distance)
class Solution {
    public int maxArea(int[] height) {
        // Start from left to right
        // have 2 indices. the right index will shrink

        int largestAmount = 0;

        for (int leftIndex = 0; leftIndex < (height.length - 1); leftIndex++) {
            if (height[leftIndex] == 0) {
                continue;
            }
            for (int rightIndex = height.length - 1; rightIndex > leftIndex; rightIndex--) {
                if (height[rightIndex] == 0) {
                    continue;
                }
                int distance = rightIndex - leftIndex;
                int shortestHeight = Math.min(height[leftIndex], height[rightIndex]);
                int amount = distance * shortestHeight;

                if (amount > largestAmount) {
                    largestAmount = amount;
                }

                // optimisation: the current biggest beats the largest possible water amount
                int nextPossibleLargestAmount = height[leftIndex] * (distance - 1);
                if(nextPossibleLargestAmount <= largestAmount) {
                    break;
                }
            }
        }

        return largestAmount;
    }
}