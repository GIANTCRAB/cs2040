package leetcode.mergeksortedlists

import java.util.*

class Solution {
    private val fullData = PriorityQueue<Int>()

    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        // loop through the respective listnodes in the array, space-time estimate: O(nm)
        lists.forEach {
            if (it != null) {
                var toLoop = true
                var nodeToSeek = it
                while (toLoop) {
                    if (nodeToSeek != null) {
                        val nodeValue = nodeToSeek.`val`
                        fullData.add(nodeValue)
                        nodeToSeek = nodeToSeek.next
                    }

                    if (nodeToSeek == null) {
                        toLoop = false
                    }
                }
            }
        }

        var listNode: ListNode? = null
        var listNodeLatestPointer: ListNode? = null
        // Reconstruct queue/fibonacci heap into listnode
        while (fullData.isNotEmpty()) {
            val integerData = fullData.poll()
            if (listNode == null || listNodeLatestPointer == null) {
                // Construct first ListNode
                listNode = ListNode(integerData)
                listNodeLatestPointer = listNode
            } else {
                listNodeLatestPointer.next = ListNode(integerData)
                listNodeLatestPointer = listNodeLatestPointer.next
            }
        }
        return listNode
    }
}