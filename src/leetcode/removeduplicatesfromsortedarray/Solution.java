package leetcode.removeduplicatesfromsortedarray;

// This uses in place methods such as a modified insertion sort
// TODO: Make it faster
class Solution {
    public int removeDuplicates(int[] nums) {
        final int BLANK_VALUE = 1000;
        boolean doLoop = true;
        int currentIndex = 0;
        int removals = 0;
        Integer previousValue = null;
        Integer currentValue;
        while (doLoop) {
            currentValue = nums[currentIndex];
            // skip if on blank
            if (currentValue.equals(BLANK_VALUE)) {
                currentIndex++;
                continue;
            }
            if (previousValue != null) {
                if (previousValue.equals(currentValue)) {
                    // Remove current value by setting it to BLANK_VALUE
                    nums[currentIndex] = BLANK_VALUE;
                    currentIndex++;
                    removals++;
                } else {
                    previousValue = nums[currentIndex];
                    currentIndex++;
                }
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

    public void modifiedInsertionSort(int[] nums) {
        if (nums.length > 1) {
            boolean doSwap = true;
            int currentIndex = 0;
            int forwardIndex = 1;
            while (doSwap) {
                final int currentValue = nums[currentIndex];
                final int forwardValue = nums[forwardIndex];
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

                if (currentIndex + 1 == nums.length) {
                    doSwap = false;
                }
            }
        }
    }
}