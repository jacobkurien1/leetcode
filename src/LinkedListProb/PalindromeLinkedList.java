package LinkedListProb;

/*
https://leetcode.com/problems/palindrome-linked-list/
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?
 */
/*
Solution: reverse the second half of the list and check whether both halves are palindrome.
Make sure to take care of even+odd number of elements in the list.
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null ){
            slow = slow.next;
            fast = fast.next==null?null:fast.next.next;
        }
        ListNode revSecondHalf = reverseList(slow);
        while(revSecondHalf !=null){
            if(head.val != revSecondHalf.val){
                return false;
            }
            head = head.next;
            revSecondHalf = revSecondHalf.next;
        }
        return true;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr!= null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
