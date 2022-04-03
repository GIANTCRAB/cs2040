package leetcode.firstmissingpositive;

import java.util.Arrays;

// Passes the test but really slow and huge memory use!
// TODO: improve speed
class Solution {
    public int firstMissingPositive(int[] nums) {
        final int[] sortedArray = Arrays.stream(nums).filter(result -> result > 0).distinct().sorted().toArray();
        int smallestInt = 1;
        for (int i = 0; i < sortedArray.length; i++) {
            if(sortedArray[i] == smallestInt) {
                smallestInt++;
            } else {
                break;
            }
        }
        return smallestInt;
    }
}