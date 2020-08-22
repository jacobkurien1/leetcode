package Trees;
/*
https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list.
 For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor,
and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.

Example 1:
Input: root = [4,2,5,1,3]
Output: [1,2,3,4,5]

Example 2:
Input: root = [2,1,3]
Output: [1,2,3]

Example 3:
Input: root = []
Output: []
Explanation: Input is an empty tree. Output is also an empty Linked List.

Example 4:
Input: root = [1]
Output: [1]


Constraints:

-1000 <= Node.val <= 1000
Node.left.val < Node.val < Node.right.val
All values of Node.val are unique.
0 <= Number of Nodes <= 2000
 */
/*
Solution: keep a prev pointer. Do an inorder traversal on the tree and keep adding the last element in the inorder traversal to prev.
In the main method, make the linkedlist circular.
Running time is O(n).
Space required is O(n) for recursive solution.
 */
public class BstToCircularDoublyLinkedList {
    Node prev = new Node(0);
    public Node treeToDoublyList(Node root) {
        Node head = prev;
        treeToDoublyListUtil(root);
        Node firstNode= head.right;
        if(firstNode != null){
            firstNode.left = prev;
            prev.right = firstNode;
        }

        return firstNode;
    }

    public void treeToDoublyListUtil(Node root){
        if(root == null){
            return;
        }
        treeToDoublyListUtil(root.left);
        prev.right = root;
        root.left = prev;
        prev = prev.right;
        treeToDoublyListUtil(root.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }
        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}
