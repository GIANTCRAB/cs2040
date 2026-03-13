package leetcode.missingnumber;

class Solution {
    public int missingNumber(int[] nums) {
        // This seems like an easy problem of storing visited numbers
        // Actually, maybe we can have a variable called expectedSum or something, from our arithmatic sequencing
        // (n/2) * (2a + ((n-1) * d)) where d is the difference (+1) and a is the first number of the sequence
        // this can be reduced to: (n/2) * (n-1)
        int n = (nums.length + 1);
        int expectedSum = (int) Math.round(((double) n / 2) * (n - 1));
        int currentSum = 0;

        for (int idx = 0; idx < nums.length; idx++) {
            currentSum += nums[idx];
        }
        return expectedSum - currentSum;
    }
}