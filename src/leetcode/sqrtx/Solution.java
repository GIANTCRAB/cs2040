package leetcode.sqrtx;

public class Solution {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        long currentGuess = x;
        while (currentGuess > x / currentGuess) {
            currentGuess = ((x / currentGuess) + currentGuess) / 2;
        }

        return (int) currentGuess;
    }
}