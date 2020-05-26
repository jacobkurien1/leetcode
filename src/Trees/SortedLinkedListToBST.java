package Trees;
/*
https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
 */
public class SortedLinkedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        this.head = head;
        return simulateInorder(0, getSize(head)-1);
    }

    int getSize(ListNode n){
        int size = 0;
        while(n!=null){
            size++;
            n = n.next;
        }
        return size;
    }

    ListNode head = null;

    TreeNode simulateInorder(int st, int end){
        if(st>end){
            return null;
        }
        int mid = st + (end-st)/2;
        TreeNode left = simulateInorder(st, mid-1);

        TreeNode n = new TreeNode(head.val);
        head = head.next;

        TreeNode right = simulateInorder(mid+1, end);
        n.left = left;
        n.right = right;
        return n;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    class ListNode {
        int val;
        ListNode next;
    }
}
