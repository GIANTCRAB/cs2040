package leetcode.medianoftwosortedarrays

import kotlin.math.floor

class Solution {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val mergeResult = merge(nums1, nums2)

        val mergeSize = mergeResult.size
        if (mergeSize > 0) {
            if (mergeSize.rem(2) == 0) {
                // is even
                val indexLeft: Int = (mergeSize / 2) - 1
                val indexRight: Int = (mergeSize / 2)

                return (mergeResult[indexLeft] + mergeResult[indexRight]).toDouble().div(2)
            } else {
                // is odd
                val indexCenter: Int = floor(mergeSize.toDouble().div(2)).toInt()
                return mergeResult[indexCenter].toDouble()
            }
        }

        return 0.0
    }

    private fun merge(left: IntArray, right: IntArray): List<Int> {
        var indexLeft = 0
        var indexRight = 0
        val newList: MutableList<Int> = mutableListOf()

        while (indexLeft < left.count() && indexRight < right.count()) {
            if (left[indexLeft] <= right[indexRight]) {
                newList.add(left[indexLeft])
                indexLeft++
            } else {
                newList.add(right[indexRight])
                indexRight++
            }
        }

        while (indexLeft < left.size) {
            newList.add(left[indexLeft])
            indexLeft++
        }

        while (indexRight < right.size) {
            newList.add(right[indexRight])
            indexRight++
        }

        return newList
    }
}