package leetcode.minimumsizesubarraysum;

import java.lang.Integer;

// Sliding window solution
// Initially, I came up with a naive solution that solves the problem using sliding window
// It summed all the nums from leftSliderIndex to rightSliderIndex. nums[leftSliderIndex] to nums[rightSliderIndex]
// Thereafter, it TLE'd. I realise we can optimise it further
// by having a cache amount that adds/minus based on the value that is being appended or removed
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int cache = 0;
        boolean isIncreasingSize = true;
        int bestSize = Integer.MAX_VALUE;
        int leftSliderIndex = 0;
        int rightSliderIndex = 0;
        while(leftSliderIndex < nums.length) {
            int sum = 0;
            int currentSize = rightSliderIndex - leftSliderIndex + 1;
            if(leftSliderIndex == rightSliderIndex) {
                sum = nums[leftSliderIndex];
            } else {
                if (cache == 0) {
                    for (int i = leftSliderIndex; i <= rightSliderIndex; i++) {
                        sum += nums[i];
                    }
                } else {
                    if (isIncreasingSize) {
                        sum = cache + nums[rightSliderIndex];
                    } else {
                        sum = cache - nums[leftSliderIndex - 1];
                    }
                }
            }
            cache = sum;

            if (sum >= target) {
                // Update the best size and also, we should move leftIndex forward if it is smaller than rightIndex
                if (currentSize < bestSize) {
                    bestSize = currentSize;
                }
                if (bestSize == 1) {
                    // Early return since there can be nothing smaller than 1
                    return bestSize;
                }
                if(leftSliderIndex < rightSliderIndex) {
                    leftSliderIndex++;
                    isIncreasingSize = false;
                    continue;
                }
            }
            // move the rightIndex forward if possible
            if ((rightSliderIndex + 1) < nums.length) {
                rightSliderIndex++;
                isIncreasingSize = true;
            } else {
                // We have reached the end
                break;
            }
        }
        if(bestSize != Integer.MAX_VALUE) {
            return bestSize;
        }
        return 0;
    }
}