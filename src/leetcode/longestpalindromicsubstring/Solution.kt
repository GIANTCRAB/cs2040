package leetcode.longestpalindromicsubstring

class Solution {
    /**
     * Current solution: O(n^2)
     *
     * TODO: change to use Manacher's algorithm for linear time
     */
    fun longestPalindrome(givenString: String): String {
        // pad given string with space
        val paddedString = " $givenString#"
        val paddedChar = paddedString.toCharArray()

        var evenCharToSkip = 0
        var longestLen = 1
        var oddPalindrome = true
        var index = 1
        for (i in 1..givenString.length) {
            // do odd, check left and right
            var oddDistance = 1
            while (true) {
                val backwardsIndex = i - oddDistance
                val forwardIndex = i + oddDistance
                if (backwardsIndex > 0 && forwardIndex <= givenString.length && paddedChar[backwardsIndex] == paddedChar[forwardIndex]) {
                    // palindrom detected, increase the distance to check
                    oddDistance++
                } else {
                    val currentLen = oddDistance * 2 + 1
                    if (currentLen > longestLen) {
                        oddPalindrome = true
                        longestLen = currentLen
                        index = i
                    }
                    break
                }
            }

            if (evenCharToSkip <= 0) {
                var evenDistance = 1
                val startingPosition = i + 1
                while (true) {
                    val backwardsIndex = startingPosition - evenDistance
                    val forwardIndex = i + evenDistance
                    if (backwardsIndex > 0 && forwardIndex <= givenString.length && paddedChar[backwardsIndex] == paddedChar[forwardIndex]) {
                        evenDistance++
                    } else {
                        val currentLen = evenDistance * 2
                        if (currentLen > longestLen) {
                            oddPalindrome = false
                            longestLen = currentLen
                            index = i
                        }
                        break
                    }
                }
            } else {
                evenCharToSkip--
            }
        }

        var palindromeString = ""
        if (oddPalindrome) {
            val pivot = paddedChar[index]
            palindromeString += pivot
            val distance = (longestLen - 1) / 2
            for (i in 1 until distance) {
                val pointedChar = paddedChar[index + i]
                palindromeString = pointedChar + palindromeString + pointedChar
            }
        } else {
            val distance = longestLen / 2
            for (i in 1 until distance) {
                val pointedChar = paddedChar[index + i]
                palindromeString = pointedChar + palindromeString + pointedChar
            }
        }

        return palindromeString
    }
}