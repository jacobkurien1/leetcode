package main.java.LinkedListProb;

/*
https://leetcode.com/problems/swap-nodes-in-pairs/
Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes. Only nodes itself may be changed.



Example 1:


Input: head = [1,2,3,4]
Output: [2,1,4,3]
Example 2:

Input: head = []
Output: []
Example 3:

Input: head = [1]
Output: [1]


Constraints:

The number of nodes in the list is in the range [0, 100].
0 <= Node.val <= 100
 */
/*
Running time is O(n)
Space needed is O(1)
*/
public class SwapNodesInPair {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode prev = head;
        ListNode curr = head.next;
        ListNode swappedHead = head.next;
        ListNode lastEnd = null;
        while(curr != null){
            ListNode next = curr.next;
            //Do the swap
            curr.next = prev;

            //Update the lastEnd's next
            //Save the end after swap
            if(lastEnd !=null) lastEnd.next = curr;
            lastEnd = prev;
            lastEnd.next = null;

            // Move pointers 2 step
            prev = next;
            curr = (next == null? null:next.next);

        }
        if(prev != null){
            lastEnd.next = prev;
        }
        return swappedHead;
    }
    class ListNode {
        ListNode(int val){
            this.val = val;
        }
        int val;
        ListNode next;
    }
}
