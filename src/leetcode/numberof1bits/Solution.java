package leetcode.numberof1bits;

class Solution {
    // Simple recursion can solve this
    // Reminder: decimal to binary => divide by 2
    // modulo by 2 -> result is 1 == 1 bit, result is 0 == 0 bit
    public int hammingWeight(int n) {
        return genSetBits(n, 0);
    }

    private int genSetBits(int remainder, int setBits) {
        if(remainder == 0) {
            return setBits;
        }

        int moduloOutcome = remainder % 2;
        int updatedRemainder = remainder / 2;
        if(moduloOutcome == 1) {
            return genSetBits(updatedRemainder, setBits + 1);
        }

        return genSetBits(updatedRemainder, setBits);
    }
}
