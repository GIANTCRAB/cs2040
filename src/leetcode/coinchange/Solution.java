package leetcode.coinchange;

class Solution {
    // we cannot go for a pure greedy solution because this is the classic coin change problem
    // since this is a BFS question, we will need a heap/queue
    // maybe we can maximise the ones that have the least change count thus far
    // we can have a priority heap that maximise least change count
    // and we will also need to maintain a visited set to track what remainingAmount has been visited
    // managed to pass TLE but still shit perf
    // change to linkedlist instead of heap. this gave better performance
    // let's rethink the problem as Dynamic Programming instead
    // here are the states:
    // coin number, current amount, change count
    // global state: target amount
    // when given coins 1, 2, 5 with target 8
    // we could be going to the same place in the same number of attempts
    // or going: 1, 2, 5
    // or going: 5, 2, 1
    // or going: 2, 5, 1
    // all 3 resulted in count of 3 and reaching target of 8
    // maybe we can use a Map to maintain a previous state
    // Map<amount, changeCount>
    // maybe int[] is more efficient as we already know the target amount
    // we start from a small amount and keep going upwards
    // this builds up a previous cache that we can rely on
    // faster now but still not great
    // let's try to improve it
    // we can probably not need to pass change count parameter since we're doing bottom up approach
    // we are doing bottom up, not top down DP. we don't need to loop behind us, just access cache directly
    // things: diff = targetAmount - coin
    // if cache of the diff exists, then we can use the amount
    // and if the cache of the diff is -1, it might mean we can't reach from here either

    public int coinChange(int[] coins, int amount) {
        if(amount == 0) {
            return 0;
        }

        int[] cache = new int[amount + 1];

        for(int currentTargetAmount = 0; currentTargetAmount <= amount; currentTargetAmount++) {
            genCoinChange(cache, coins, currentTargetAmount);
        }

        if(cache[amount] == 0) {
            return -1;
        }

        return cache[amount];
    }

    private void genCoinChange(int[] cache, int[] coins, int targetAmount) {
        for(int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            int diff = targetAmount - coin;
            if(diff > 0) {
                if(cache[diff] != 0) {
                    if(cache[diff] == -1) {
                        // Potentially not reachable as well
                        if(cache[targetAmount] == 0) {
                            cache[targetAmount] = -1;
                        }
                    } else {
                        int coinChangeCount = cache[diff] + 1;
                        if(cache[targetAmount] == 0) {
                            cache[targetAmount] = coinChangeCount;
                        } else {
                            cache[targetAmount] = Math.min(cache[targetAmount], coinChangeCount);
                        }
                    }
                }
            }
            if(diff == 0) {
                cache[targetAmount] = 1;
            }
        }
    }
}