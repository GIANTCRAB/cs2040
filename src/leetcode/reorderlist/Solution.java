package leetcode.reorderlist;

import java.util.*;

class Solution {
    public void reorderList(ListNode head) {
        // a non-optimal solution i can think of immediately
        // would be to use a HashMap to store the nodes
        // Tried and this works just fine, slow performance but no TLE.
        // but perhaps there may be a better way to handle this
        // We can probably use tortoise/hare cycle detection method to reverse the LL
        // I am thinking, the hare should store a previous pointer
        // Maybe let's do a stack instead first to understand better
        // We will have one future pointer  so we can keep moving
        // + one fast pointer so we can store the stack
        // We keep removing stuff
        // Stack is better performance but still bad
        // Maybe we use some sort of recursion that stores previousPointer
        // this is because in a way, recursion is a form of Stack
        // TODO: change to use recursion
        Stack<ListNode> stack = new Stack<>();
        ListNode currentPointer = head;
        while(currentPointer != null) {
            stack.push(currentPointer);
            currentPointer = currentPointer.next;
        }

        ListNode actualNext = head.next;
        currentPointer = head;
        while(actualNext != null) {
            actualNext = currentPointer.next;
            if(!stack.isEmpty() && actualNext != null) {
                ListNode backPointer = stack.pop();
                if(actualNext.equals(backPointer)) {
                    backPointer.next = null;
                    break;
                } else {
                    if(currentPointer.equals(backPointer)) {
                        currentPointer.next = null;
                        break;
                    }
                    currentPointer.next = backPointer;
                    backPointer.next = actualNext;
                    currentPointer = actualNext;
                }
            }
        }
    }
}