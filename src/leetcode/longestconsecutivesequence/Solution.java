package leetcode.longestconsecutivesequence;

import java.util.Arrays;

// I am thinking about the numbers. there's probably no need for hash
// since I need it to be continuous, perhaps I can try sorting them
// Java has built-in merge sort via Arrays.sort
// After I sorted the numbers, it will be easy to see if they're in sequence
// I am assuming that the sequence goes nums[i] + 1 == nums[i + 1] . if and only if.
// and when the condition is not satisfied, we are no longer in a sequence anymore
// However, I missed out on cases such as the value == nextValue, that can be added in easily
// I also missed out on the edge case of 0 length nums.
// As well as edge case for sequence being started counting when we have 1 number
public class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int longestSequenceLength = 1;
        int currentSequenceLength = 1;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++) {
            int value = nums[i];
            int nextValue = nums[i + 1];

            if (value + 1 == nextValue) {
                currentSequenceLength++;
                if(currentSequenceLength > longestSequenceLength) {
                    longestSequenceLength = currentSequenceLength;
                }
                continue;
            }
            if (value == nextValue) {
                continue;
            }
            currentSequenceLength = 1;
        }

        return longestSequenceLength;
    }
}
