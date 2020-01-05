package Trees;

/*
https://leetcode.com/problems/invert-binary-tree/
Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
