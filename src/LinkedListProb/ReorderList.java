package LinkedListProb;
/*
https://leetcode.com/problems/reorder-list/
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
/*
Running time is O(n)
Space needed is O(1)
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        // get Node after mid
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null){
            slow = slow.next;
            fast = fast.next;
            if(fast != null){
                fast = fast.next;
            }
        }

        //Reverse from slow to end
        ListNode prev = slow;
        ListNode curr = (slow!=null)?slow.next:null;
        if(slow != null){
            slow.next = null;
        }
        ListNode next = (curr == null)?null:curr.next;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // intermigle the list from 0 -> mid and mid+1->end
        ListNode stHead = head;
        ListNode midHead = prev;
        while(midHead != null){
            ListNode stHeadNext = stHead.next;
            stHead.next = midHead;
            stHead = stHeadNext;
            ListNode midHeadNext = midHead.next;
            midHead.next = stHeadNext;
            midHead = midHeadNext;
        }
        if(stHead != null){
            stHead.next = null;
        }
    }
    class ListNode {
        ListNode(int val){
            this.val = val;
        }
        int val;
        ListNode next;
    }
}
