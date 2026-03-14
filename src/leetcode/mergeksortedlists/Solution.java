package leetcode.mergeksortedlists;

import java.util.*;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // I'm thinking of a simple recursion loop
        // If i encounter TLE, i will need to re-think
        // The initial solution seems to work without TLE
        // I am now thinking if i can use a PriorityQueue / heap
        // Smallest heap value first works
        Queue<ListNode> heap = new PriorityQueue<>((nodeOne, nodeTwo) -> nodeOne.val - nodeTwo.val); // smallest value first

        for(int i = 0; i < lists.length; i++) {
            ListNode listNode = lists[i];
            if(listNode == null) {
                continue;
            }
            heap.add(listNode);
        }

        ListNode head = null;
        ListNode currentPointer = null;
        while(!heap.isEmpty()) {
            ListNode smallestNode = heap.poll();

            if(currentPointer == null) {
                currentPointer = smallestNode;
            } else {
                ListNode previousCurrentPointer = currentPointer;
                currentPointer = smallestNode;
                previousCurrentPointer.next = smallestNode;
            }

            if(smallestNode.next != null) {
                heap.add(smallestNode.next);
            }

            if(head == null) {
                head = currentPointer;
            }
        }

        return head;
    }
}