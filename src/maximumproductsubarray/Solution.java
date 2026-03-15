package maximumproductsubarray;

import java.lang.*;

class Solution {
    public int maxProduct(int[] nums) {
        // Firstly, we might not be able to sort because ordering matters
        // another thing to note: negative multiplied by negative would grant a positive number
        // a 0 value would nullify any big numbers
        // We can use a sliding window / 2 pointer here
        // we can have an expanding sliding window
        // and store a cache of the previous value
        // we should stop expanding when our product becomes 0
        // performance is not good
        // trying some optimisation to end loop earlier
        // optimisation works and improved significantly, but still quite often
        // maybe the idea would be to split the number into sub-arrays by 0
        // maybe the better idea is to create sub-array groups separated by negative numbers
        // hence these sub-array groups will always be positive
        // the adjacent sub-array groups cannot be extended if there is zero in between them
        // subarray group data: startingIndex, endingIndex, productSum
        // we also store indices of negative numbers in a Set<Integer>
        // unexplainable gaps between sub-array groups can then be assumed to be 0
        // after creating these sub-array groups, we then do another set of operations to try to join them?
        // by using some logic to join. TODO: think further about joining logic
        // never mind, this is probably to use kadane's algorithm
        // we need to modify kadane's algorithm to fit this
        // I overcomplicated the logic
        // Reduce complication of logic and try to understand kadane's algo better
        if(nums.length == 1) {
            return nums[0];
        }

        int largestProduct = Integer.MIN_VALUE;
        int previousMaxProduct = 0;
        int previousMinProduct = 0;

        for(int i = 0; i < nums.length; i++) {
            int currentNumber = nums[i];
            int previousMaxOutcome = currentNumber * previousMaxProduct;
            int previousMinOutcome = currentNumber * previousMinProduct;
            previousMaxProduct = Math.max(Math.max(currentNumber, previousMaxOutcome), previousMinOutcome);
            previousMinProduct = Math.min(Math.min(currentNumber, previousMaxOutcome), previousMinOutcome);

            if(previousMaxProduct > largestProduct) {
                largestProduct = previousMaxProduct;
            }
        }

        return largestProduct;
    }
}