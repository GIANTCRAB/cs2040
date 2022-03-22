package leetcode.medianoftwosortedarrays;

// 5ms runtime
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return getMedianOfArray(nums2);
        }
        if (nums2.length == 0) {
            return getMedianOfArray(nums1);
        }

        int[] mergedNums = new int[nums1.length + nums2.length];
        int mergedArrayIndex = 0;
        int num1Index = 0;
        int num2Index = 0;
        while (mergedArrayIndex < mergedNums.length) {
            if (num1Index < nums1.length) {
                if (num2Index < nums2.length) {
                    final int firstValue = nums1[num1Index];
                    final int secondValue = nums2[num2Index];
                    if (firstValue < secondValue) {
                        mergedNums[mergedArrayIndex] = firstValue;
                        num1Index++;
                    } else {
                        mergedNums[mergedArrayIndex] = secondValue;
                        num2Index++;
                    }
                    mergedArrayIndex++;
                } else {
                    // nums1 still can go on but nums2 is done
                    mergedNums[mergedArrayIndex] = nums1[num1Index];
                    num1Index++;
                    mergedArrayIndex++;
                }
            } else {
                // nums2 still can go on but nums1 is done
                mergedNums[mergedArrayIndex] = nums2[num2Index];
                num2Index++;
                mergedArrayIndex++;
            }
        }

        return getMedianOfArray(mergedNums);
    }

    public double getMedianOfArray(int[] singleArray) {
        final boolean isEven = (singleArray.length % 2) == 0;
        if (isEven) {
            final int halfPointIndex = singleArray.length / 2;
            return (((double) singleArray[halfPointIndex - 1]) + ((double) singleArray[halfPointIndex])) / 2;
        } else {
            final int halfPointIndex = (int) Math.floor(((double) singleArray.length) / 2);
            return singleArray[halfPointIndex];
        }
    }
}
