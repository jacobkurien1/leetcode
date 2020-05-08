package Trees;

/*
https://leetcode.com/problems/cousins-in-binary-tree/
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.



Example 1:


Input: root = [1,2,3,4], x = 4, y = 3
Output: false
Example 2:


Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true
Example 3:



Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false


Note:

The number of nodes in the tree will be between 2 and 100.
Each node has a unique integer value from 1 to 100.
 */
/*
Running time is O(n)
Space needed is O(n) in case of a skewed tree
 */
public class CousinsInBT {
    public boolean isCousins(TreeNode root, int x, int y) {
        treeTraversal(root, x, y, null, 0);
        return (parentX != null && parentY != null &&
                parentX.val != parentY.val && xlevel == ylevel);
    }

    TreeNode parentX = null;
    TreeNode parentY = null;
    int xlevel = Integer.MAX_VALUE;
    int ylevel = Integer.MAX_VALUE;

    void treeTraversal(TreeNode n, int x, int y, TreeNode parent, int level){
        if(n == null || level > xlevel || level > ylevel){ // level check will do early pruning
            return;
        }
        if(n.val == x){
            parentX = parent;
            xlevel = level;
        } else if(n.val == y){
            parentY = parent;
            ylevel = level;
        }

        treeTraversal(n.left, x, y, n, level+1);
        treeTraversal(n.right, x, y, n, level+1);
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
