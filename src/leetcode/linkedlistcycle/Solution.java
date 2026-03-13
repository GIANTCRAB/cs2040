package leetcode.linkedlistcycle;

class Solution {
    // my initial naive solution was to store value (integer) into a HashSet
    // Then found out that the values could contain duplicates
    // I then change to use HashSet with ListNode as type and that worked
    // However, this solution is sub-optimal as tortoise and hare is a better solution
    // and we only just need the first part of a tortoise and hare
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode tortoiseHead = head;
        ListNode hareHead = head.next;

        while(tortoiseHead != null && hareHead != null) {
            if(tortoiseHead.equals(hareHead)) {
                return true;
            }
            tortoiseHead = tortoiseHead.next;
            if(hareHead.next != null) {
                hareHead = hareHead.next.next;
            } else {
                return false;
            }
        }

        return false;
    }
}
