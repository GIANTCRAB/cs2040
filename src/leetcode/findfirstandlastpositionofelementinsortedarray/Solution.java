package leetcode.findfirstandlastpositionofelementinsortedarray;

// Very similar to searchinrotatedsortedarray
class Solution {
    public int[] searchRange(int[] nums, int target) {
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
                    } else {
                        if (nums[mostLeftIndex] == target) {
                            // halt the left index
                            leftHalt = true;
                        } else {
                            if (nums[mostLeftIndex] > target) {
                                // not found
                                break;
                            }
                        }
                    }
                }

                if (!rightHalt) {
                    if (nums[mostRightIndex] > target) {
                        // move rightIndex backward
                        mostRightIndex--;
                    } else {
                        if (nums[mostRightIndex] == target) {
                            // halt the right index
                            rightHalt = true;
                        } else {
                            if (nums[mostRightIndex] < target) {
                                // not found
                                break;
                            }
                        }
                    }
                }

                if (leftHalt && rightHalt) {
                    return new int[]{mostLeftIndex, mostRightIndex};
                }
            }
        } else {
            if (nums.length > 0 && nums[0] == target) {
                return new int[]{0, 0};
            }
        }
        return new int[]{-1, -1};
    }
}