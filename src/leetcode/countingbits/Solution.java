package leetcode.countingbits;

class Solution {
    // The question seems like there would be some kind of recurrence relation
    // It looks similar to fib sequence
    // it can be solved with recursion
    // Then i used a HashMap to store previous data
    // then i realised i dont need a hash map as my array of int[] would already contain previous data
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            result[i] = countBitByIndex(i, result);
        }
        return result;
    }

    public int countBitByIndex(int idx, int[] previousData) {
        if(idx == 0) {
            return 0;
        }
        if(idx == 1) {
            return 1;
        }
        if(previousData[idx] != 0) {
            return previousData[idx];
        }
        int remainder = idx % 2;
        int quotient = idx / 2;
        int result = remainder + countBitByIndex(quotient, previousData);

        return result;
    }
}