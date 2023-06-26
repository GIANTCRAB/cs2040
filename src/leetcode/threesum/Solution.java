package leetcode.threesum;

import java.util.*;

// TODO: Improve run time performance
class Solution {
    public List<List<Integer>> threeSum(int[] sortedArray) {
        final Set<List<Integer>> fullSet = new HashSet<>();
        // Sort the given array first
        Arrays.sort(sortedArray);
        int firstFixedIndex = 0;
        int firstMovingIndex = 1;
        int secondMovingIndex = sortedArray.length - 1;
        while(firstFixedIndex + 2 < sortedArray.length) {
            while(firstMovingIndex < secondMovingIndex) {
                final int sumResult = sortedArray[firstFixedIndex] + sortedArray[firstMovingIndex] + sortedArray[secondMovingIndex];
                if(sumResult == 0) {
                    final List<Integer> newList = new ArrayList<>();
                    newList.add(sortedArray[firstFixedIndex]);
                    newList.add(sortedArray[firstMovingIndex]);
                    newList.add(sortedArray[secondMovingIndex]);
                    fullSet.add(newList);
                    firstMovingIndex++;
                } else {
                    if (sumResult < 0) {
                        // negative right now, need to move firstMovingIndex forward towards a positive number
                        firstMovingIndex++;
                    } else {
                        // positive right now, need to move secondMovingIndex backwards towards a negative number
                        secondMovingIndex--;
                    }
                }
            }
            firstFixedIndex++;
            firstMovingIndex = firstFixedIndex + 1;
            secondMovingIndex = sortedArray.length - 1;
        }

        return new ArrayList<>(fullSet);
    }
}