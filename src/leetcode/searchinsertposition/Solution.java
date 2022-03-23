package leetcode.searchinsertposition;

class Solution {
    public int searchInsert(int[] nums, int target) {
        int foundIndex = -1;
        final int arrayLength = nums.length;
        if (arrayLength > 1) {
            boolean leftHalt = false;
            boolean rightHalt = false;

            // start in the center and shift left/right accordingly
            int currentIndex = (int) Math.floor((double) arrayLength / 2.0) - 1;
            if (nums[currentIndex] < target) {
                rightHalt = true;
            } else {
                leftHalt = true;
            }
            while (currentIndex != -1 && currentIndex != arrayLength) {
                if (nums[currentIndex] < target) {
                    if (!leftHalt) {
                        currentIndex++;
                    } else {
                        // Not found, so insert at where it passed by
                        foundIndex = currentIndex + 1;
                        break;
                    }
                } else {
                    if (nums[currentIndex] == target) {
                        // found the value position
                        foundIndex = currentIndex;
                        break;
                    } else {
                        if (nums[currentIndex] > target) {
                            if (!rightHalt) {
                                currentIndex--;
                            } else {
                                // Not found, so insert at where it passed by
                                foundIndex = currentIndex;
                                break;
                            }
                        }
                    }
                }
            }

            // Not found anywhere, so insert at the start of end accordingly
            if (currentIndex == -1) {
                return 0;
            }
            if (currentIndex == arrayLength) {
                return arrayLength;
            }
        } else {
            if (arrayLength > 0) {
                return (nums[0] < target) ? 1 : 0;
            } else {
                return 0;
            }
        }
        return foundIndex;
    }
}