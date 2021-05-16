package leetcode.twosum

class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val arrangedNums = HashMap<Int, Int>()
        val result = IntArray(2)
        // value -> index of original array
        nums.forEachIndexed lit@{ index, value ->
            run {
                val otherValRequired = target - value

                if (arrangedNums.containsKey(otherValRequired)) {
                    val otherIndex = arrangedNums[otherValRequired]
                    result[0] = otherIndex!!
                    result[1] = index
                    return@lit
                }

                // Assume unique
                arrangedNums[value] = index
            }
        }

        return result
    }
}