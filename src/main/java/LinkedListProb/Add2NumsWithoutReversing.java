package LinkedListProb;
/*
https://leetcode.com/problems/add-two-numbers-ii/submissions/
You are given two non-empty linked lists representing two non-negative integers.
The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
 */
/*
Running time is O(n)
Space needed is O(n) to save the output
 */
public class Add2NumsWithoutReversing {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int size1 = getSize(l1);
        int size2 = getSize(l2);
        ListNode sumNode;
        if(size1 > size2){
            sumNode = addNum(l1, l2, size1, size2);
        } else {
            sumNode = addNum(l2, l1, size2, size1);
        }
        return (carry == 0)? sumNode: new ListNode (carry, sumNode);
    }
    int carry = 0;

    ListNode addNum(ListNode l1, ListNode l2, int i1, int i2){
        ListNode nextNode;
        int sum;
        if(i1 != i2){
            nextNode = addNum(l1.next, l2, --i1, i2);
            sum = l1.val+ carry;
        } else {
            if(l1 == null){
                return null;
            }
            nextNode = addNum(l1.next, l2.next, --i1, --i2);
            sum = l1.val + l2.val + carry;

        }
        carry = sum/10;
        ListNode curr = new ListNode(sum%10);
        curr.next = nextNode;
        return curr;
    }

    int getSize(ListNode n){
        int size = 0;
        while(n!=null){
            ++size;
            n = n.next;
        }
        return size;
    }
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
