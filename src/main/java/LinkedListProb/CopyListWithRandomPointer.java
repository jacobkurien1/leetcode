package LinkedListProb;

/*
https://leetcode.com/problems/copy-list-with-random-pointer/
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.


Example 1:


Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
Example 2:


Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
Example 3:



Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
Example 4:

Input: head = []
Output: []
Explanation: Given linked list is empty (null pointer), so return null.


Constraints:

-10000 <= Node.val <= 10000
Node.random is null or pointing to a node in the linked list.
Number of Nodes will not exceed 1000.
 */
/*
We do it in 3 passes
Running time is O(n)
Space needed is O(n) to keep the copy
 */
public class CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        Node curr = head;
        Node headNew = null;
        while(curr!= null){
            Node currNew = new Node(curr.val);
            if(headNew == null){
                headNew = currNew;
            }
            currNew.next = curr.next;
            curr.next = currNew;
            curr = currNew.next;
        }
        curr = head;
        while(curr!= null){
            Node currNew = curr.next;
            currNew.random = (curr.random==null)?null:curr.random.next;
            curr = currNew.next;
        }
        curr = head;
        while(curr!= null){
            Node currNew = curr.next;
            curr.next = currNew.next;
            currNew.next = (currNew.next == null)?null:currNew.next.next;
            curr = curr.next;
        }
        return headNew;
    }
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
