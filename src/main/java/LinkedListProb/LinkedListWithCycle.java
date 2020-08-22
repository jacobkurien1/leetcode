package LinkedListProb;

/*
https://leetcode.com/problems/linked-list-cycle/
Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to.
If pos is -1, then there is no cycle in the linked list.



Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.


Example 2:

Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.


Example 3:

Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.




Follow up:

Can you solve it using O(1) (i.e. constant) memory?
 */
/*
Running time is O(n)
Space is O(1)
 */
public class LinkedListWithCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
            if(fast != null){
                fast = fast.next;
            }
            if(fast != null && fast == slow){
                return true;
            }
        }
        return false;
    }
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
