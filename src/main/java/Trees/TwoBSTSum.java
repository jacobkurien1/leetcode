package Trees;

import java.util.Stack;

/*
https://leetcode.com/problems/two-sum-bsts/
Given two binary search trees, return True if and only if there is a node in the first tree
and a node in the second tree whose values sum up to a given integer target.

Example 1:
Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
Output: true
Explanation: 2 and 3 sum up to 5.

Example 2:
Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
Output: false


Constraints:

Each tree has at most 5000 nodes.
-10^9 <= target, node.val <= 10^9
 */
/*
Here go to the left most in BST1 and go to the right most in BST2.
Check whether the sum of both these nodes is equal to target.
if the sum is less than target, then go right in BST1
if sum is greater than target, then go left in BST2.

Running time O(N1 + N2)
Space is O(N1 + N2)
 */
public class TwoBSTSum {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Stack<TreeNode> st1 = new Stack<TreeNode>();
        Stack<TreeNode> st2 = new Stack<TreeNode>();
        while(true){
            while(root1 != null){
                st1.push(root1);
                root1 = root1.left;
            }

            while(root2 != null){
                st2.push(root2);
                root2 = root2.right;
            }
            if(st1.isEmpty() || st2.isEmpty()){
                return false;
            }

            TreeNode left = st1.peek();
            TreeNode right = st2.peek();

            if(left.val + right.val == target){
                return true;
            } else if (left.val + right.val < target){
                // get the next value from left
                TreeNode n = st1.pop();
                if(n.right != null){
                    root1 = n.right;
                }
            } else {
                // get the next value from right
                TreeNode n = st2.pop();
                if(n.left != null){
                    root2 = n.left;
                }
            }
        }
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
