package leetcode.mergetwosortedlists;

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // First, find out which list we need to start with
        // Whoever is the starting node, we will return it after the recursions of dive
        if(list1 != null) {
            if(list2 != null) {
                if(list1.val < list2.val) {
                    dive(list1, list2);
                    return list1;
                } else {
                    dive(list2, list1);
                    return list2;
                }
            } else {
                return list1;
            }
        } else {
            return list2;
        }
    }

    private void dive(ListNode currentNode, ListNode otherNode) {
        final ListNode nextNode = currentNode.next;
        if(nextNode != null) {
            if(nextNode.val < otherNode.val) {
                // Continue diving, no change of pointers required
                dive(nextNode, otherNode);
            } else {
                // Since the other node has a smaller value, we change who to dive into.
                // Thereafter, we also want to change the pointer of the current's "next" to the other node
                dive(otherNode, nextNode);
                currentNode.next = otherNode;
            }
        } else {
            // Since there is no next node, we can end the dive and immediately point to the other node
            currentNode.next = otherNode;
        }
    }

    private class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}