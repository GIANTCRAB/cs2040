package leetcode.coinchange;

import java.util.Arrays;

// TODO: fix it for all cases
public class Solution {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        int[] filteredCoins = Arrays.stream(coins).filter((int coin) -> coin <= amount).sorted().toArray();

        return solve(filteredCoins, amount, 0, 0);
    }

    public int solve(int[] sortedCoins, int amount, int coinsSum, int coinsCount) {
        for (int coinIndex = sortedCoins.length - 1; coinIndex >= 0; coinIndex--) {
            final int pickedCoin = sortedCoins[coinIndex];
            coinsSum += pickedCoin;
            coinsCount++;
            if (coinsSum == amount) {
                return coinsCount;
            }
            if (coinsSum < amount) {
                final int isSolved = solve(sortedCoins, amount, coinsSum, coinsCount);
                if (isSolved == -1) {
                    coinsSum -= pickedCoin;
                    coinsCount--;
                } else {
                    return isSolved;
                }
            }
        }
        return -1;
    }
}