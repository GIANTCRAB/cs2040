package leetcode.searchinsertposition.alternative1;

// Start from both left and right sides and slowly center in.
// Supposedly uses more memory because there are more pointers to keep track of
class Solution {
    public int searchInsert(int[] nums, int target) {
        int foundIndex;
        if (nums.length > 1) {
            int mostLeftIndex = 0;
            int mostRightIndex = nums.length - 1;
            while (true) {
                if (nums[mostLeftIndex] < target) {
                    // move leftIndex forward
                    mostLeftIndex++;
                } else {
                    if (nums[mostLeftIndex] == target) {
                        // found the result
                        foundIndex = mostLeftIndex;
                        break;
                    } else {
                        if (nums[mostLeftIndex] > target) {
                            // not found so insert at where it passed by
                            foundIndex = mostLeftIndex;
                            break;
                        }
                    }
                }

                if (nums[mostRightIndex] > target) {
                    // move rightIndex backward
                    mostRightIndex--;
                } else {
                    if (nums[mostRightIndex] == target) {
                        foundIndex = mostRightIndex;
                        break;
                    } else {
                        if (nums[mostRightIndex] < target) {
                            // not found so insert at where it passed by
                            foundIndex = mostRightIndex + 1;
                            break;
                        }
                    }
                }
            }
        } else {
            if (nums.length > 0) {
                return (nums[0] < target) ? 1 : 0;
            } else {
                return 0;
            }
        }
        return foundIndex;
    }
}