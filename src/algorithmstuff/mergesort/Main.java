package algorithmstuff.mergesort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] exampleArray = new int[]{5, 8, 2, 1, 0, -5, 5000, 4, 503, 999 ,222};
        mergeSort(exampleArray);
    }

    public static void mergeSort(int[] startingArray) {
        topDownSplitMerge(startingArray, 0, startingArray.length);
    }

    public static int[] topDownSplitMerge(int[] workingArray, int indexStart, int indexEnd) {
        if(indexEnd - indexStart <= 1) {
            return workingArray;
        }
        final int indexMiddle = (indexStart + indexEnd) / 2;
        workingArray = topDownSplitMerge(workingArray, indexStart, indexMiddle);
        workingArray = topDownSplitMerge(workingArray, indexMiddle, indexEnd);
        return topDownMerge(workingArray, indexStart, indexMiddle, indexEnd);
    }

    public static int[] topDownMerge(int[] workingArray, int indexStart, int indexMiddle, int indexEnd) {
        final int[] referenceArray = Arrays.copyOf(workingArray, workingArray.length);
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
        return workingArray;
    }
}
