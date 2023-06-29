package algorithmstuff.mergesort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] exampleArray = new int[]{3, 1, 99, 2, -5, 500, 5000, 66};
        mergeSort(exampleArray);
    }

    public static void mergeSort(int[] startingArray) {
        // We need pointers for this, convert them into array list
        final List<Integer> workingArray = Arrays.stream(startingArray)
                .boxed()
                .collect(Collectors.toList());

        final List<Integer> referenceArray = new ArrayList<>(workingArray);
        topDownSplitMerge(workingArray, 0, startingArray.length, referenceArray);
    }

    public static void topDownSplitMerge(List<Integer> workingArray, int indexStart, int indexEnd, List<Integer> referenceArray) {
        if(indexEnd - indexStart <= 1) {
            return;
        }
        final int indexMiddle = (indexStart + indexEnd) / 2;
        topDownSplitMerge(referenceArray, indexStart, indexMiddle, workingArray);
        topDownSplitMerge(referenceArray, indexMiddle, indexEnd, workingArray);
        topDownMerge(workingArray, indexStart, indexMiddle, indexEnd, referenceArray);
    }

    public static void topDownMerge(List<Integer> workingArray, int indexStart, int indexMiddle, int indexEnd, List<Integer> referenceArray) {
        int movingLeftIndex = indexStart;
        int movingRightIndex = indexMiddle;
        for (int i = indexStart; i < indexEnd; i++) {
            if(movingLeftIndex < indexMiddle && (movingRightIndex >= indexEnd || referenceArray.get(movingLeftIndex) <= referenceArray.get(movingRightIndex))) {
                workingArray.set(i, referenceArray.get(movingLeftIndex));
                movingLeftIndex++;
            } else {
                workingArray.set(i, referenceArray.get(movingRightIndex));
                movingRightIndex++;
            }
        }
        System.out.println(workingArray);
    }
}
