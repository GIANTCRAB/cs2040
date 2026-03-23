package leetcode.rotatearray;

class Solution {
    // it should be possible to do it in O(1) space and O(n) perf
    // we can hold the previous value in a variable
    // steps:
    // take start index 0 and save the value as x
    // move to 0 + k index
    // save 0 + k index value as y
    // change 0 + k index value to x
    // move to 0 + k + k index
    // if it exceeds the index, continue counting from 0 (think of circular graph)
    // save 0 + k + k index value as z
    // change 0 + k + k value to y
    // keep doing it until we have done it n times.
    // however, there are cases where we will encounter us landing back at the starting index
    // this means we need another variable to store the starting index
    // whenever we land back at the starting index, increment it
    public void rotate(int[] nums, int k) {
        if(nums.length == 1 || k == 0) {
            return;
        }

        int startingIndex = 0;
        int currentIndex = 0;
        int previousValue = nums[startingIndex];
        for(int opCount = 0; opCount < nums.length; opCount++) {
            currentIndex = currentIndex + k;
            if(currentIndex >= nums.length) {
                currentIndex = currentIndex % nums.length;
            }
            int currentValue = nums[currentIndex];
            if(currentIndex == startingIndex) {
                nums[currentIndex] = previousValue;
                currentIndex++;
                startingIndex++;
                if(startingIndex == nums.length) {
                    break;
                }
                previousValue = nums[currentIndex];
                continue;
            }
            nums[currentIndex] = previousValue;
            previousValue = currentValue;
        }
    }
}
