package leetcode.nextpermutation;

// TODO: I need to make it more readable
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length > 1) {
            final int finalIndex = nums.length - 1;
            int currentIndex = finalIndex;
            int forwardIndex = currentIndex - 1;
            boolean swapped = false;

            while (!swapped && forwardIndex > -1) {
                while (currentIndex > forwardIndex) {
                    // Can we swap to make it bigger?
                    if (nums[currentIndex] > nums[forwardIndex]) {
                        final int tempForwardValue = nums[forwardIndex];
                        nums[forwardIndex] = nums[currentIndex];
                        nums[currentIndex] = tempForwardValue;
                        swapped = true;
                        break;
                    } else {
                        // can't swap, move the current index forward
                        currentIndex--;
                    }
                }
                if (!swapped) {
                    forwardIndex--;
                    currentIndex = finalIndex;
                }
            }

            if (swapped) {
                // Since the array was enlarged, it needs to now be reduced
                // For example: [5,4,8,3]
                // Swapped would be: [5,8,4,3]
                // But it can be reduced to: [5,8,3,4]
                // Reduction is only required behind the swap point: [5,8***4,3] -> [4,3]
                reduceArray(nums, forwardIndex);
            } else {
                // this is the largest permutation, inverse the whole array to make it the smallest
                invertArray(nums);
            }
        }
    }

    private void reduceArray(int[] nums, int swapPointIndex) {
        // Swap from most extreme left
        final int finalIndex = nums.length - 1;
        int currentIndex = finalIndex;
        int forwardIndex = swapPointIndex + 1;
        while (forwardIndex < finalIndex) {
            while (currentIndex > forwardIndex) {
                if (nums[forwardIndex] > nums[currentIndex]) {
                    // do swap
                    final int tempForwardValue = nums[forwardIndex];
                    nums[forwardIndex] = nums[currentIndex];
                    nums[currentIndex] = tempForwardValue;
                }
                currentIndex--;
            }
            currentIndex = finalIndex;
            forwardIndex++;
        }
    }

    private void invertArray(int[] nums) {
        final int arrayLength = nums.length;
        final int arrayFinalIndex = arrayLength - 1;
        final int stopIndexPoint;
        if (arrayLength % 2 == 0) {
            // even array
            stopIndexPoint = (arrayLength / 2);
        } else {
            // odd array
            stopIndexPoint = (int) Math.floor(((double) arrayLength) / 2);
        }

        // swap from most extreme left and right points. then slowly approach the center
        for (int i = 0; i < stopIndexPoint; i++) {
            final int tempFirstValue = nums[i];
            nums[i] = nums[arrayFinalIndex - i];
            nums[arrayFinalIndex - i] = tempFirstValue;
        }
    }
}