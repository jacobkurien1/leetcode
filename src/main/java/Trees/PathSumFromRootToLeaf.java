package main.java.Trees;
/*
https://leetcode.com/problems/path-sum/
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

 */
/*
Running time is O(n)
Space needed is O(n) for a skewed tree
 */
public class PathSumFromRootToLeaf {
    public boolean hasPathSum(TreeNode root, int sum) {
        return hasPathSumRecur(root, sum);
    }

    int currSum = 0;

    boolean hasPathSumRecur(TreeNode n, int sum){
        if(n== null){
            return false;
        }
        currSum += n.val;
        if(currSum == sum && n.left == null && n.right == null){
            return true;
        }
        if( hasPathSumRecur(n.left, sum) || hasPathSumRecur(n.right, sum)){
            return true;
        }
        currSum -= n.val;
        return false;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
