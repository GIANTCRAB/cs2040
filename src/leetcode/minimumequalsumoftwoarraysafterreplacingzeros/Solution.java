package leetcode.minimumequalsumoftwoarraysafterreplacingzeros;

class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        // i think we need to know the number of zeros available
        // and i think the easiest way to "cheat" the system is to replace all 0s
        // with 1; except for 1 for the zero on either side whichever is smaller
        // the special zero will then be used for the remaining sum equalising
        // actually, we don't even need the special zero
        // we can flip every 0 to 1 and check whether the smaller side has zeros or not
        // if the smaller side has zeros, we can return the larger side's amount
        // ah, i forgot to change to use long for the summing
        boolean numOneHasZero = false;
        boolean numTwoHasZero = false;

        long numOneSum = 0;
        long numTwoSum = 0;

        for(int i = 0; i < nums1.length; i++) {
            int num1 = nums1[i];

            if(num1 == 0) {
                numOneHasZero = true;
                numOneSum += 1;
            } else {
                numOneSum += num1;
            }
        }

        for(int i = 0; i < nums2.length; i++) {
            int num2 = nums2[i];

            if(num2 == 0) {
                numTwoHasZero = true;
                numTwoSum += 1;
            } else {
                numTwoSum += num2;
            }
        }

        if(numOneSum < numTwoSum && !numOneHasZero) {
            return -1;
        }

        if(numOneSum > numTwoSum && !numTwoHasZero) {
            return -1;
        }

        return Math.max(numOneSum, numTwoSum);
    }
}
