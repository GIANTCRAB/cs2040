package leetcode.removeduplicatesfromsortedarray;

// This uses in place methods such as a modified insertion sort
// It uses less memory but it is quite slow.
// TODO: Make it faster while maintaining low memory footprint
class Solution {
    final int BLANK_VALUE = 1000;

    public int removeDuplicates(int[] nums) {
        if (nums.length > 1) {
            boolean doLoop = true;
            int currentIndex = 1;
            int removals = 0;
            int previousValue = nums[currentIndex - 1];
            int currentValue;
            while (doLoop) {
                currentValue = nums[currentIndex];
                if (previousValue == currentValue) {
                    // Remove current value by setting it to BLANK_VALUE
                    nums[currentIndex] = BLANK_VALUE;
                    currentIndex++;
                    removals++;
                } else {
                    previousValue = nums[currentIndex];
                    currentIndex++;
                }

                if (currentIndex == nums.length) {
                    doLoop = false;
                }
            }

            modifiedInsertionSort(nums);

            return nums.length - removals;
        }

        return nums.length;
    }

    public void modifiedInsertionSort(int[] nums) {
        boolean doSwap = true;
        int currentIndex = 0;
        int forwardIndex = 1;
        while (doSwap) {
            final int currentValue = nums[currentIndex];
            final int forwardValue = nums[forwardIndex];
            if (currentValue == BLANK_VALUE) {
                // swap when larger
                if (currentValue > forwardValue) {
                    nums[forwardIndex] = currentValue;
                    nums[currentIndex] = forwardValue;
                    // reset index after swapping
                    currentIndex++;
                    forwardIndex = currentIndex + 1;
                } else {
                    forwardIndex++;
                    // reset index if needed
                    if (forwardIndex == nums.length) {
                        currentIndex++;
                        forwardIndex = currentIndex + 1;
                    }
                }
            } else {
                currentIndex++;
                forwardIndex = currentIndex + 1;
            }

            if (currentIndex + 1 == nums.length) {
                doSwap = false;
            }
        }
    }
}