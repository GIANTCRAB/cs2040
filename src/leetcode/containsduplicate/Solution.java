package leetcode.containsduplicate;

import java.util.Arrays;

// Super-fast solution using modified Merge Sort. Faster than 80% of the solutions.
// Using try-catch exception to exit the call stack from recursion
// Maybe there's a better way to do it so that the memory usage is lesser?
class Solution {
    public boolean containsDuplicate(int[] nums) {
        try {
            mergeSort(nums);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public static void mergeSort(int[] startingArray) throws Exception {
        int[] referenceArray = Arrays.copyOf(startingArray, startingArray.length);
        topDownSplitMerge(startingArray, 0, startingArray.length, referenceArray);
    }

    public static void topDownSplitMerge(int[] workingArray, int indexStart, int indexEnd, int[] referenceArray) throws Exception {
        if(indexEnd - indexStart <= 1) {
            return;
        }
        final int indexMiddle = (indexStart + indexEnd) / 2;
        topDownSplitMerge(referenceArray, indexStart, indexMiddle, workingArray);
        topDownSplitMerge(referenceArray, indexMiddle, indexEnd, workingArray);
        topDownMerge(workingArray, indexStart, indexMiddle, indexEnd, referenceArray);
    }

    public static void topDownMerge(int[] workingArray, int indexStart, int indexMiddle, int indexEnd, int[] referenceArray) throws Exception {
        int movingLeftIndex = indexStart;
        int movingRightIndex = indexMiddle;
        for (int i = indexStart; i < indexEnd; i++) {
            if(movingLeftIndex < indexMiddle && (movingRightIndex >= indexEnd || referenceArray[movingLeftIndex] <= referenceArray[movingRightIndex])) {
                if(movingRightIndex < indexEnd && referenceArray[movingLeftIndex] == referenceArray[movingRightIndex]) {
                    throw new Exception("duplicate");
                }
                workingArray[i] = referenceArray[movingLeftIndex];
                movingLeftIndex++;
            } else {
                workingArray[i] = referenceArray[movingRightIndex];
                movingRightIndex++;
            }
        }
    }
}