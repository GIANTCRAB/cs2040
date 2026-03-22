package leetcode.jumpgame;

class Solution {
    // upon first look, this seems like a bottom-up DP question
    // we probably need some form of cache for previous results
    // what are the states we need to hold?
    // max jump length for each index
    // we work backwards from the last index because that is the base case
    // we can use a boolean[] cache to store whether it can reach the end
    // that solved the problem but it seems sub-optimal
    // surely there's a way to optimise the backwards looking
    // I realise that we only need to store a cache, which is the leftMostIndex
    // this removes the need for a loop lookup on a boolean[] cache
    public boolean canJump(int[] nums) {
        if(nums.length == 1) {
            return true;
        }

        int leftMostIndex = nums.length - 1;

        for(int i = nums.length - 2; i >= 0; i--) {
            int minJumpRequired = nums.length - i - 1;
            int currentNumber = nums[i];
            int diff = currentNumber - minJumpRequired;
            if(diff >= 0) {
                leftMostIndex = i;
            } else {
                if(currentNumber + i >= leftMostIndex) {
                    leftMostIndex = i;
                }
            }
        }

        return leftMostIndex == 0;
    }
}