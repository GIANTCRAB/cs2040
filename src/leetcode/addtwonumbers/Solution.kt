package leetcode.addtwonumbers

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    private var returnResult: ListNode? = null
    private var resultLastPointer: ListNode? = null

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val THRESHOLD = 10
        var hasEnded: Pair<Boolean, Boolean> = Pair(first = false, second = false)
        if (l1 == null) {
            hasEnded = Pair(first = true, second = hasEnded.second)
        }
        if (l2 == null) {
            hasEnded = Pair(first = hasEnded.first, second = true)
        }
        var firstListNode = l1
        var secondListNode = l2

        var loop = true
        var broughtOverValue = 0
        while (loop) {
            var firstNumber = 0
            if (!hasEnded.first) {
                firstNumber = firstListNode!!.`val`

                if (firstListNode.next == null) {
                    hasEnded = Pair(first = true, second = hasEnded.second)
                } else {
                    firstListNode = firstListNode.next
                }
            }

            var secondNumber = 0
            if (!hasEnded.second) {
                secondNumber = secondListNode!!.`val`

                if (secondListNode.next == null) {
                    hasEnded = Pair(first = hasEnded.first, second = true)
                } else {
                    secondListNode = secondListNode.next
                }
            }

            // Add both numbers and brought over value
            val sum = firstNumber + secondNumber + broughtOverValue
            if (sum >= THRESHOLD) {
                val finalSum = sum - THRESHOLD
                this.appendToNode(finalSum)
                broughtOverValue = 1
            } else {
                this.appendToNode(sum)
                broughtOverValue = 0
            }

            if (hasEnded.first && hasEnded.second) {
                if (broughtOverValue > 0) {
                    this.appendToNode(broughtOverValue)
                }
                loop = false
            }
        }

        return this.returnResult
    }

    private fun appendToNode(value: Int) {
        if (this.returnResult == null) {
            this.returnResult = ListNode(value)
            this.resultLastPointer = this.returnResult
            return
        }

        if (this.resultLastPointer != null) {
            this.resultLastPointer!!.next = ListNode(value)
            this.resultLastPointer = this.resultLastPointer!!.next
        }
    }
}