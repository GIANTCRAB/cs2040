package leetcode.spiralmatrix;

import java.util.*;

class Solution {
    // upon the first look, it seems like there is some form of recurrence relation
    // maybe we just need to keep decreasing top-bottom max index
    // same for left-right max index
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int numberOfRows = matrix.length;
        int numberOfColumns = matrix[0].length;
        int topMax = 0;
        int bottomMax = numberOfRows - 1;
        int leftMax = 0;
        int rightMax = numberOfColumns - 1;

        int direction = 0; // 0 is going right, 1 is going down, 2 is going left, 3 is going up, then it repeats
        int currentRowIndex = 0;
        int currentColumnIndex = 0;

        while(topMax <= bottomMax && leftMax <= rightMax) {
            if(direction == 0) {
                if(currentColumnIndex == rightMax) {
                    // change direction
                    direction++;
                    topMax++;
                }
            }
            if(direction == 1) {
                if(currentRowIndex == bottomMax) {
                    // change direction
                    direction++;
                    rightMax--;
                }
            }
            if(direction == 2) {
                if(currentColumnIndex == leftMax) {
                    // change direction
                    direction++;
                    bottomMax--;
                }
            }
            if(direction == 3) {
                if(currentRowIndex == topMax) {
                    // reset direction
                    direction = 0;
                    leftMax++;
                }
            }

            result.add(matrix[currentRowIndex][currentColumnIndex]);

            if(direction == 0) {
                currentColumnIndex++;
            }
            if(direction == 1) {
                currentRowIndex++;
            }
            if(direction == 2) {
                currentColumnIndex--;
            }
            if(direction == 3) {
                currentRowIndex--;
            }
        }

        return result;
    }
}