package leetcode.containerwithmostwater;

// TODO: Fix Time Limit Exceeded
public class Solution {
    public int maxArea(int[] height) {
        int largestArea = 0;
        for (int i = 0; i < height.length; i++) {
            final int startPointHeight = height[i];
            for (int j = (i + 1); j < height.length; j++) {
                final int endPointHeight = height[j];
                final int distanceBetweenPoints = j - i;
                final int calculatedArea = Math.min(startPointHeight, endPointHeight) * distanceBetweenPoints;

                if (calculatedArea > largestArea) {
                    largestArea = calculatedArea;
                }
            }
        }

        return largestArea;
    }
}

