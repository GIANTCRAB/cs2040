package leetcode.searchinrotatedsortedarraytwo;

// This is extremely similar to searchinrotatedsortedarray
class Solution {
    public boolean search(int[] nums, int target) {
        boolean canBeFound = false;
        if (nums.length > 1) {
            boolean leftHalt = false;
            boolean rightHalt = false;
            int mostLeftIndex = 0;
            int mostRightIndex = nums.length - 1;
            while (true) {
                if (!leftHalt) {
                    if (nums[mostLeftIndex] < target) {
                        // move leftIndex forward
                        mostLeftIndex++;
                        if (mostLeftIndex == nums.length) {
                            // not found
                            leftHalt = true;
                        }
                    } else {
                        if (nums[mostLeftIndex] == target) {
                            // found the value position
                            canBeFound = true;
                            break;
                        } else {
                            if (nums[mostLeftIndex] > target) {
                                // not found
                                leftHalt = true;
                            }
                        }
                    }
                }

                if (!rightHalt) {
                    if (nums[mostRightIndex] > target) {
                        // move rightIndex backward
                        mostRightIndex--;
                        if (mostRightIndex == -1) {
                            // not found
                            rightHalt = true;
                        }
                    } else {
                        if (nums[mostRightIndex] == target) {
                            // found the value position
                            canBeFound = true;
                            break;
                        } else {
                            if (nums[mostRightIndex] < target) {
                                // not found
                                rightHalt = true;
                            }
                        }
                    }
                }

                if (leftHalt && rightHalt) {
                    // not found
                    break;
                }
            }
        } else {
            return nums.length > 0 && nums[0] == target;
        }

        return canBeFound;
    }
}