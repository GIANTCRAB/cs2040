package leetcode.longestsubstringwithoutrepeatingcharacters

class Solution {
    private var longestLen = 0
    private var fullMap = HashMap<Char, Int>()
    private var windowStart = 0
    private var windowEnd = 0

    fun lengthOfLongestSubstring(givenString: String): Int {
        givenString.forEachIndexed { index, value ->
            windowEnd += 1
            if (this.fullMap.containsKey(value)) {
                val previousIndex: Int = this.fullMap[value]!!
                // reframe
                // prevent reframing from too behind
                if (previousIndex >= windowStart) {
                    windowStart = previousIndex + 1
                }
            }
            this.fullMap[value] = index
            val newWindowSize = windowEnd - windowStart
            if (this.longestLen < newWindowSize) {
                this.longestLen = newWindowSize
            }
        }

        return this.longestLen
    }
}