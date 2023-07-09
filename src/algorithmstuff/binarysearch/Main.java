package algorithmstuff.binarysearch;

public class Main {
    public static void main(String[] args) {
        final int result = binarySearch(new int[]{-1,0,3,5,9,12}, 9, 0, 5);
        System.out.println(result);
    }

    public static int binarySearch(int[] givenArray, int target, int startIndex, int endIndex) {
        if(givenArray.length == 1) {
            if(givenArray[0] == target) {
                return 0;
            } else {
                return -1;
            }
        }

        if(endIndex - startIndex == 0) {
            if(givenArray[startIndex] == target) {
                return startIndex;
            }
        } else {
            final int middleIndex = startIndex + ((endIndex - startIndex) / 2);
            if(givenArray[middleIndex] == target) {
                return middleIndex;
            } else {
                if (givenArray[middleIndex] < target) {
                    // Focus on right search
                    return binarySearch(givenArray, target, middleIndex + 1, endIndex);
                } else {
                    // Focus on left search
                    return binarySearch(givenArray, target, startIndex, middleIndex - 1);
                }
            }
        }

        return -1;
    }
}
