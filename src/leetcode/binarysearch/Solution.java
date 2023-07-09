package leetcode.binarysearch;

public class Solution {
    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public static int binarySearch(int[] givenArray, int target, int startIndex, int endIndex) {
        if(givenArray.length == 1) {
            if(givenArray[0] == target) {
                return 0;
            } else {
                return -1;
            }
        }

        if(endIndex - startIndex == 0) {
            if(givenArray[startIndex] == target) {
                return startIndex;
            }
        } else {
            final int middleIndex = startIndex + ((endIndex - startIndex) / 2);
            if(givenArray[middleIndex] == target) {
                return middleIndex;
            } else {
                if (givenArray[middleIndex] < target) {
                    // Focus on right search
                    final int newStartIndex = Math.min(endIndex, middleIndex + 1);
                    return binarySearch(givenArray, target, newStartIndex, endIndex);
                } else {
                    // Focus on left search
                    final int newEndIndex = Math.max(startIndex, middleIndex - 1);
                    return binarySearch(givenArray, target, startIndex, newEndIndex);
                }
            }
        }

        return -1;
    }
}