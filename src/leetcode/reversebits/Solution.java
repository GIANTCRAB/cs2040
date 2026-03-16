package leetcode.reversebits;

import java.util.*;
import java.lang.*;

class Solution {
    // Mathematical problem, not super interesting
    // need to remember how to derive integer from bits, and vice versa
    // we can divide something by 2 non-stop to get bit form
    // we can do Math.pow(2, n) to derive decimal from bit
    public int reverseBits(int n) {
        boolean[] outcome = integerToReverseBits(new boolean[32], n, 0);
        return bitsToInt(outcome, 0, 0);
    }

    private boolean[] integerToReverseBits(boolean[] bitState, int remainder, int count) {
        int moduloOutcome = remainder % 2;
        int updatedRemainder = remainder / 2;
        bitState[31 - count] = moduloOutcome == 1 ? true : false;

        if(updatedRemainder == 0) {
            return bitState;
        }

        return integerToReverseBits(bitState, updatedRemainder, count + 1);
    }

    private int bitsToInt(boolean[] bitState, int totalSum, int index) {
        if(index >= bitState.length) {
            return totalSum;
        }
        if(bitState[index]) {
            int updateTotalSum = totalSum + (int) Math.pow(2, index);
            return bitsToInt(bitState, updateTotalSum, index + 1);
        }

        return bitsToInt(bitState, totalSum, index + 1);
    }
}