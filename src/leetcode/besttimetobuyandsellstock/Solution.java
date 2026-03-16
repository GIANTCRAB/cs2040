package leetcode.besttimetobuyandsellstock;

class Solution {
    public int maxProfit(int[] prices) {
        // constraint. the buy date must be before the sell date. so ordering matters
        // we probably can do this in a single pass
        // and i dont think this is a sliding window or 2 pointer
        // I am thinking maybe it is related to Kadane's algorithm
        // think of selling as a plus
        // think of buying as a negative
        // we want to store differences, not individual buy/sell numbers
        int biggestProfit = 0;
        int currentSum = 0;

        for(int i = 1; i < prices.length; i++) {
            int currentDiff = prices[i] - prices[i - 1];
            currentSum = Math.max(currentDiff, currentSum + currentDiff);
            biggestProfit = Math.max(biggestProfit, currentSum);
        }

        return biggestProfit;
    }
}