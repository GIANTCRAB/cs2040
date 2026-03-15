package leetcode.maximumsubarray;

import java.lang.*;

class Solution {
    // Immediately identified as Kadane's algorithm
    // Solvable using O(n)
    public int maxSubArray(int[] nums) {
        int bestSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for(int idx = 0; idx < nums.length; idx++) {
            int amount = nums[idx];
            currentSum = Math.max(amount, currentSum + amount);
            bestSum = Math.max(currentSum, bestSum);
        }

        return bestSum;
    }
}
