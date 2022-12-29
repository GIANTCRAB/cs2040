package leetcode.reverselinkedlist;

/**
 * "In-place" reversal and has low memory footprint
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        return dive(null, head, null);
    }

    public ListNode dive(ListNode previousNode, ListNode currentNode, ListNode lastNode) {
        if(currentNode != null) {
            if(currentNode.next != null) {
                // Result has been set, return it upwards
                lastNode = dive(currentNode, currentNode.next, lastNode);
            } else {
                // Set last node
                lastNode = currentNode;
            }
            currentNode.next = previousNode;
        }
        return lastNode;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}