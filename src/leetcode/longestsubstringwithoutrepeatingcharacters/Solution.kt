package leetcode.longestsubstringwithoutrepeatingcharacters

class Solution {
    private var longestLen = 0
    private var window = HashMap<Char, Int>()

    fun lengthOfLongestSubstring(givenString: String): Int {
        givenString.asSequence().forEachIndexed { index, value ->
            if (this.window.containsKey(value)) {
                // Remove everything until wherever it exists
                val previousIndex: Int = this.window[value]!!
                val windowIterator = this.window.iterator()
                while (windowIterator.hasNext()) {
                    val charData = windowIterator.next()
                    if (charData.value <= previousIndex) {
                        windowIterator.remove()
                    }
                }
            }
            this.window[value] = index
            if (this.longestLen < this.window.size) {
                this.longestLen = this.window.size
            }
        }

        return this.longestLen
    }
}