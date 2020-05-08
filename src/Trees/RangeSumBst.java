package Trees;

/*
https://leetcode.com/problems/range-sum-of-bst/
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.



Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23


Note:

The number of nodes in the tree is at most 10000.
The final answer is guaranteed to be less than 2^31.
 */
public class RangeSumBst {
    public int rangeSumBST(TreeNode root, int L, int R) {
        rangeSumRecur(root, L, R);
        return rangeSum;
    }
    int rangeSum = 0;
    public void rangeSumRecur(TreeNode n, int L, int R) {
        if(n == null){
            return;
        }
        if(n.val>=L && n.val<=R){
            rangeSum+= n.val;
            rangeSumRecur(n.left, L, R);
            rangeSumRecur(n.right, L,R);
        } else if(n.val <L){
            rangeSumRecur(n.right, L, R);
        } else {
            rangeSumRecur(n.left, L,R);
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
