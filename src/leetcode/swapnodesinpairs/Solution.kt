package leetcode.swapnodesinpairs

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
    fun swapPairs(head: ListNode?): ListNode? {
        var firstNode: ListNode? = null
        var previousNode: ListNode? = null
        var currentNode: ListNode? = head
        var nextNode: ListNode?
        var followingNode: ListNode?
        var shouldSkip = false
        while (true) {
            if (currentNode != null) {
                if (shouldSkip) {
                    previousNode = currentNode
                    currentNode = currentNode.next
                    shouldSkip = false
                } else {
                    nextNode = currentNode.next
                    if (nextNode != null) {
                        // 1 2 3 4
                        // 2 -> 1
                        // 1 -> 3
                        followingNode = nextNode.next
                        nextNode.next = currentNode
                        currentNode.next = followingNode
                        if (previousNode != null) {
                            previousNode.next = nextNode
                        }
                        // move forward
                        previousNode = nextNode
                        shouldSkip = true

                        if (firstNode == null) {
                            firstNode = nextNode
                        }
                    } else {
                        break
                    }
                }
            } else {
                break
            }
        }

        if (firstNode == null) {
            firstNode = head
        }

        return firstNode
    }
}