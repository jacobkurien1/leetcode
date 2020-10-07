package main.java.Trees;
/*
https://leetcode.com/problems/balanced-binary-tree/
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: true
Example 2:


Input: root = [1,2,2,3,3,null,null,4,4]
Output: false
Example 3:

Input: root = []
Output: true


Constraints:

The number of nodes in the tree is in the range [0, 5000].
-104 <= Node.val <= 104
 */
/*
Running time is O(n)
Space needed is O(n) for a skewed tree
 */
public class BalancedBt {
    public boolean isBalanced(TreeNode root) {
        return getMaxHeight(root) != -1;
    }

    int getMaxHeight(TreeNode n){
        if(n == null){
            return 0;
        }
        int left = getMaxHeight(n.left);
        if(left == -1){
            return -1;
        }
        int right = getMaxHeight(n.right);
        if(right == -1 || Math.abs(left-right)>1){
            return -1;
        }
        return Math.max(left, right)+1;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
