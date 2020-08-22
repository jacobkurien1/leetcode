package LinkedListProb;

/*
https://leetcode.com/problems/reverse-linked-list-ii/
Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
 */
/*
Running time  is O(N)
Space Needed is O(1)
 */
public class ReversePartOfLinkedList {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || n <= m){
            return head;
        }
        ListNode sentinelHead = new ListNode(0);
        ListNode prev = sentinelHead;
        prev.next = head;
        ListNode curr = prev.next;
        int count = 1;
        ListNode before = null;
        ListNode sliceSt = null;

        while(prev != null){
            ListNode next = (curr!=null)?curr.next:null;
            if(count == m){
                before = prev;
                sliceSt = curr;
            } else if(count>m && count <=n){
                // reverse node
                curr.next = prev;

            } else if (count == n+1){
                before.next = prev;
                sliceSt.next = curr;
                break;
            }
            count++;
            prev = curr;
            curr = next;
        }
        return sentinelHead.next;
    }
    class ListNode {
        ListNode(int val){
            this.val = val;
        }
        int val;
        ListNode next;
    }
}
