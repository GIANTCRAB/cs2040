package leetcode.removenthnodefromendoflist;

import java.util.*;

class Solution {

    // Another pointer recursion problem with backtracking
    // This is simpler, we can return a Pair of data to indicate how much we have backtracked
    // When we reach null, we start init the pair data with null and backtrack index as 1 as to how far back we are
    // if the n == backtrack index, we do the removal
    // otherwise, return currentNode and increment the backtrack index
    // We need to consider about head being removed.
    // It should still point to the 2nd node
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Pair<ListNode, Integer> newHeadPair = removeNthFromEnd(n, null, head);
        return newHeadPair.getKey();
    }

    private Pair<ListNode, Integer> removeNthFromEnd(int n, ListNode previous, ListNode current) {
        if(current == null) {
            return new Pair<>(null, 1);
        }

        Pair<ListNode, Integer> nextNodePair = removeNthFromEnd(n, current, current.next);
        if(n == nextNodePair.getValue()) {
            if(previous == null) {
                if(current.next != null) {
                    ListNode newHead = current.next;
                    current = null;
                    // New head will be this current.next instead
                    return new Pair<>(newHead, nextNodePair.getValue() + 1);
                }
                current = null;
            } else {
                previous.next = current.next;
                current.next = null;
            }
        }

        return new Pair<>(current, nextNodePair.getValue() + 1);
    }
}