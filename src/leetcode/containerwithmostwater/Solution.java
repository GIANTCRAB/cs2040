package leetcode.containerwithmostwater;

// TODO: Make it faster
public class Solution {
    public int maxArea(int[] height) {
        final int heightLength = height.length;
        // min value, distance
        int largestArea = 0;

        // scan from left to right
        for (int leftIndex = 0; leftIndex < heightLength; leftIndex++) {
            final int startPointHeight = height[leftIndex];

            int furthestIndex = heightLength - 1;
            while (true) {
                int furthestValue = height[furthestIndex];
                if (furthestValue >= startPointHeight) {
                    final int distanceBetweenIndex = furthestIndex - leftIndex;
                    final int area = distanceBetweenIndex * startPointHeight;
                    if (area > largestArea) {
                        largestArea = area;
                    }
                    break;
                } else {
                    if (furthestIndex > leftIndex) {
                        furthestIndex--;
                    } else {
                        break;
                    }
                }
            }
        }

        // scan right to left
        for (int rightIndex = heightLength - 1; rightIndex > -1; rightIndex--) {
            final int startPointHeight = height[rightIndex];

            int furthestIndex = 0;
            while (true) {
                int furthestValue = height[furthestIndex];
                if (furthestValue >= startPointHeight) {
                    final int distanceBetweenIndex = rightIndex - furthestIndex;
                    final int area = distanceBetweenIndex * startPointHeight;
                    if (area > largestArea) {
                        largestArea = area;
                    }
                    break;
                } else {
                    if (furthestIndex < rightIndex) {
                        furthestIndex++;
                    } else {
                        break;
                    }
                }
            }
        }

        return largestArea;
    }
}

