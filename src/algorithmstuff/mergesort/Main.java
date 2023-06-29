package algorithmstuff.mergesort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] exampleArray = new int[]{3, 1, 99, 2, -5, 500, 5000, 66};
        mergeSort(exampleArray);
    }

    public static void mergeSort(int[] startingArray) {
        int[] referenceArray = Arrays.copyOf(startingArray, startingArray.length);
        topDownSplitMerge(startingArray, 0, startingArray.length, referenceArray);
    }

    public static void topDownSplitMerge(int[] workingArray, int indexStart, int indexEnd, int[] referenceArray) {
        if(indexEnd - indexStart <= 1) {
            return;
        }
        final int indexMiddle = (indexStart + indexEnd) / 2;
        topDownSplitMerge(referenceArray, indexStart, indexMiddle, workingArray);
        topDownSplitMerge(referenceArray, indexMiddle, indexEnd, workingArray);
        topDownMerge(workingArray, indexStart, indexMiddle, indexEnd, referenceArray);
    }

    public static void topDownMerge(int[] workingArray, int indexStart, int indexMiddle, int indexEnd, int[] referenceArray) {
        int movingLeftIndex = indexStart;
        int movingRightIndex = indexMiddle;
        for (int i = indexStart; i < indexEnd; i++) {
            if(movingLeftIndex < indexMiddle && (movingRightIndex >= indexEnd || referenceArray[movingLeftIndex] <= referenceArray[movingRightIndex])) {
                workingArray[i] = referenceArray[movingLeftIndex];
                movingLeftIndex++;
            } else {
                workingArray[i] = referenceArray[movingRightIndex];
                movingRightIndex++;
            }
        }
        System.out.println(Arrays.toString(workingArray));
    }
}
