package leetcode.climbingstairs;

import java.util.*;

class Solution {
    private Map<Integer, Integer> fibCache = new HashMap<>();

    public int climbStairs(int n) {
        // this looks similar to n choose k (binomial coefficients)
        // if given n = 5
        // 1 + 1 + 1 + 1 + 1
        // 1 + 1 + 1 + 2
        // 1 + 1 + 2 + 1
        // 1 + 2 + 1 + 1
        // 2 + 1 + 1 + 1
        // 1 + 2 + 2
        // 2 + 1 + 2
        // 2 + 2 + 1
        // answer should be 8
        // the more i stare at it, it looks more like a coin change problem but with ordering
        // this leads me to believe that it is similar to fibonacci sequence recursion
        // this is actually recurrence relation. meaning this is related to fibonacci
        // TLE. I then put up a fibCache to store previous results
        // it passes with HashMap as cache
        return fibIter(n, 0);
    }

    private int fibIter(int n, int currentN) {
        if(currentN > n) {
            return 0;
        }
        if(currentN == n) {
            return 1;
        }
        int nextN = currentN + 1;
        int nextNextN = currentN + 2;
        int firstResult;
        int secondResult;
        if (fibCache.containsKey(nextN)) {
            firstResult = fibCache.get(nextN);
        } else {
            firstResult = fibIter(n, nextN);
            fibCache.put(nextN, firstResult);
        }
        if (fibCache.containsKey(nextNextN)) {
            secondResult = fibCache.get(nextNextN);
        } else {
            secondResult = fibIter(n, nextNextN);
            fibCache.put(nextNextN, secondResult);
        }
        return firstResult + secondResult;
    }
}