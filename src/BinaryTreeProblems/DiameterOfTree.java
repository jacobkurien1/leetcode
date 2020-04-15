package BinaryTreeProblems;

/*

Given a binary tree, you need to compute the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
 */
/*
Running time is O(n)
space needed is also O(n)
 */
public class DiameterOfTree {
    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if( root == null){
            return 0;
        }
        maxLenAndDiameter(root);
        return maxDiameter;

    }
    int maxLenAndDiameter(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = maxLenAndDiameter(root.left);
        int right = maxLenAndDiameter(root.right);
        maxDiameter = Math.max(maxDiameter, left+right);
        return Math.max(left, right) +1;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
