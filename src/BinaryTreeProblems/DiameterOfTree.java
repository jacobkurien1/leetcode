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
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if( root == null){
            return 0;
        }
        getDepth(root);
        return diameter;

    }
    int getDepth(TreeNode n){
        if(n.left == null && n.right == null){
            return 0;
        }
        int leftDepth = 0;
        int rightDepth = 0;
        if (n.left != null) {
            leftDepth = getDepth(n.left) +1;
        }
        if (n.right != null){
            rightDepth = getDepth(n.right) +1;
        }
        diameter = Math.max(diameter, leftDepth + rightDepth );
        return Math.max(leftDepth, rightDepth);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
