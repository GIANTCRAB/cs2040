package leetcode.threesum;

import java.util.*;

// TODO: Improve memory/space usage?
class Solution {
    public List<List<Integer>> threeSum(int[] sortedArray) {
        final List<List<Integer>> fullSet = new ArrayList<>();
        // Sort the given array first
        Arrays.sort(sortedArray);
        int firstMovingIndex;
        int secondMovingIndex;
        for(int firstFixedIndex = 0; firstFixedIndex + 2 < sortedArray.length; firstFixedIndex++) {
            firstMovingIndex = firstFixedIndex + 1;
            secondMovingIndex = sortedArray.length - 1;

            // If the number is the same as the one behind, we can skip to the next loop
            if(firstFixedIndex > 0 && (sortedArray[firstFixedIndex] == sortedArray[firstFixedIndex-1])) {
                continue;
            }
            // We can stop the loop if the left side is no longer negative
            if (sortedArray[firstFixedIndex] + sortedArray[firstMovingIndex] > 0) {
                break;
            }

            while(firstMovingIndex < secondMovingIndex) {
                final int sumResult = sortedArray[firstFixedIndex] + sortedArray[firstMovingIndex] + sortedArray[secondMovingIndex];
                if(sumResult == 0) {
                    final List<Integer> newList = new ArrayList<>();
                    newList.add(sortedArray[firstFixedIndex]);
                    newList.add(sortedArray[firstMovingIndex]);
                    newList.add(sortedArray[secondMovingIndex]);
                    fullSet.add(newList);
                    do {
                        firstMovingIndex++;
                    } while (firstMovingIndex < secondMovingIndex && sortedArray[firstMovingIndex - 1] == sortedArray[firstMovingIndex]);
                    do {
                        secondMovingIndex--;
                    } while (secondMovingIndex > firstMovingIndex && sortedArray[secondMovingIndex + 1] == sortedArray[secondMovingIndex]);
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
        }

        return fullSet;
    }
}