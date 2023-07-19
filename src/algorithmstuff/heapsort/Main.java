package algorithmstuff.heapsort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] items = new int[]{40,3,5,9,12};
        sort(items);
        System.out.println(Arrays.toString(items));
    }

    public static void sort(int[] items) {
        // Form a max heap
        for (int i =  (items.length / 2) - 1; i >= 0; i--) {
            heapify(items, items.length, i);
        }

        int smallestItemIndex = items.length - 1;
        for (int i = smallestItemIndex; i > 0; i--) {
            int temp = items[0]; // Largest item since this is a max heap
            items[0] = items[i];
            items[i] = temp; // Swap the smallest item
            // Run again on a reduced heap
            heapify(items, i, 0);
        }
    }

    // left side of node index is always 2N + 1, right side of node index is always 2N + 2
    public static void heapify(int[] items, int heapSize, int nodeIndex) {
        int largestIndex = nodeIndex;
        int leftIndex = 2 * nodeIndex + 1;
        int rightIndex = 2 * nodeIndex + 2;

        if(leftIndex < heapSize && items[leftIndex] > items[largestIndex]) {
            largestIndex = leftIndex;
        }
        if(rightIndex < heapSize && items[rightIndex] > items[largestIndex]) {
            largestIndex = rightIndex;
        }

        if(largestIndex != nodeIndex) {
            // Swap the lower node upwards and then recurse
            int temp = items[nodeIndex];
            items[nodeIndex] = items[largestIndex];
            items[largestIndex] = temp;

            heapify(items, heapSize, largestIndex);
        }
    }
}
