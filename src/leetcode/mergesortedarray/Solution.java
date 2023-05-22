package leetcode.mergesortedarray;

import java.util.ArrayList;

//TODO: make it in-place swapping
public class Solution {
    public void merge(int[] leftArray, int leftArrayEndPoint, int[] rightArray, int rightArrayLength) {
        if (rightArrayLength == 0) {
            return;
        }

        final ArrayList<Integer> integerArrayList = new ArrayList<>();

        int leftArrayIndex = 0;
        int rightArrayIndex = 0;

        while (leftArrayIndex < leftArrayEndPoint || rightArrayIndex < rightArrayLength) {
            if (leftArrayIndex == leftArrayEndPoint) {
                integerArrayList.add(rightArray[rightArrayIndex]);
                rightArrayIndex++;
            } else {
                if (rightArrayIndex == rightArrayLength) {
                    integerArrayList.add(leftArray[leftArrayIndex]);
                    leftArrayIndex++;
                } else {
                    if (leftArray[leftArrayIndex] < rightArray[rightArrayIndex]) {
                        integerArrayList.add(leftArray[leftArrayIndex]);
                        leftArrayIndex++;
                    } else {
                        if (leftArray[leftArrayIndex] == rightArray[rightArrayIndex]) {
                            integerArrayList.add(leftArray[leftArrayIndex]);
                            integerArrayList.add(rightArray[rightArrayIndex]);
                            leftArrayIndex++;
                            rightArrayIndex++;
                        } else {
                            integerArrayList.add(rightArray[rightArrayIndex]);
                            rightArrayIndex++;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < integerArrayList.size(); i++) {
            leftArray[i] = integerArrayList.get(i);
        }
    }
}
