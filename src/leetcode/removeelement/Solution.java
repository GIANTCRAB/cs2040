package leetcode.removeelement;

class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums.length > 0) {
            int removalCount = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == val) {
                    nums[i] = -1;
                    removalCount++;
                }
            }
            swapToBack(nums);
            return nums.length - removalCount;
        }
        return 0;
    }

    private void swapToBack(int[] nums) {
        int startIndex = 0;
        int endIndex = nums.length - 1;
        while (startIndex < endIndex) {
            if (nums[endIndex] == -1) {
                endIndex--;
                continue;
            }

            // swap when needed
            if (nums[startIndex] == -1) {
                nums[startIndex] = nums[endIndex];
                nums[endIndex] = -1;
                endIndex--;
            }
            startIndex++;
        }
    }
}