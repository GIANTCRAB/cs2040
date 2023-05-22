package leetcode.mergesortedarray;

//TODO: make it in-place swapping
public class Solution {
    public void merge(int[] leftArray, int leftArrayEndPoint, int[] rightArray, int rightArrayLength) {
        if (rightArrayLength == 0) {
            return;
        }

        int[] integerArray = new int[leftArray.length];

        int leftArrayIndex = 0;
        int rightArrayIndex = 0;
        int indexCounter = 0;

        while (leftArrayIndex < leftArrayEndPoint || rightArrayIndex < rightArrayLength) {
            if (leftArrayIndex == leftArrayEndPoint) {
                integerArray[indexCounter] = rightArray[rightArrayIndex];
                rightArrayIndex++;
            } else {
                if (rightArrayIndex == rightArrayLength) {
                    integerArray[indexCounter] = leftArray[leftArrayIndex];
                    leftArrayIndex++;
                } else {
                    if (leftArray[leftArrayIndex] < rightArray[rightArrayIndex]) {
                        integerArray[indexCounter] = leftArray[leftArrayIndex];
                        leftArrayIndex++;
                    } else {
                        if (leftArray[leftArrayIndex] == rightArray[rightArrayIndex]) {
                            integerArray[indexCounter] = leftArray[leftArrayIndex];
                            indexCounter++;
                            integerArray[indexCounter] = rightArray[rightArrayIndex];
                            leftArrayIndex++;
                            rightArrayIndex++;
                        } else {
                            integerArray[indexCounter] = rightArray[rightArrayIndex];
                            rightArrayIndex++;
                        }
                    }
                }
            }
            indexCounter++;
        }

        System.arraycopy(integerArray, 0, leftArray, 0, integerArray.length);
    }
}
