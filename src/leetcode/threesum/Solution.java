package leetcode.threesum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// TODO: Fix Time Limit Exceeded
class Solution {
    public List<List<Integer>> threeSum(int[] nums, List<List<Long>> answer) {
        if (nums.length < 3) {
            return new ArrayList<>();
        } else {
            final HashSet<String> usedTable = new HashSet<>();
            final List<List<Integer>> fullList = new ArrayList<>();
            int firstIndex = 0;
            int secondIndex = 1;
            int thirdIndex = 2;
            final int numsLength = nums.length;
            while (firstIndex < numsLength) {
                if (secondIndex < numsLength) {
                    if (thirdIndex < numsLength) {
                        final int firstValue = nums[firstIndex];
                        final int secondValue = nums[secondIndex];
                        final int thirdValue = nums[thirdIndex];
                        if (firstValue + secondValue + thirdValue == 0 && !usedTable.contains(firstValue + "," + secondValue + "," + thirdValue)) {
                            final List<Integer> tripleList = new ArrayList<>();
                            tripleList.add(firstValue);
                            tripleList.add(secondValue);
                            tripleList.add(thirdValue);
                            fullList.add(tripleList);

                            usedTable.add(firstValue + "," + secondValue + "," + thirdValue);
                            usedTable.add(firstValue + "," + thirdValue + "," + secondValue);
                            usedTable.add(secondValue + "," + firstValue + "," + thirdValue);
                            usedTable.add(secondValue + "," + thirdValue + "," + firstValue);
                            usedTable.add(thirdValue + "," + firstValue + "," + secondValue);
                            usedTable.add(thirdValue + "," + secondValue + "," + firstValue);
                        }
                        thirdIndex++;
                    } else {
                        secondIndex++;
                        thirdIndex = secondIndex + 1;
                    }
                } else {
                    firstIndex++;
                    secondIndex = firstIndex + 1;
                    thirdIndex = secondIndex + 1;
                }

            }

            return fullList;
        }
    }
}