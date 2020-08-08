package Trees;

/*
https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
eturn the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node,
any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.
Also recall that a preorder traversal displays the value of the node first,
then traverses node.left, then traverses node.right.)

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

Note:

1 <= preorder.length <= 100
The values of preorder are distinct.
 */
/*
Running time is O(n)
Space needed is O(n)    
 */
public class BstFromPreorder {
    int index = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return getBST(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    TreeNode getBST(int[] preorder, int min, int max){
        if(index >= preorder.length || preorder[index]<=min || preorder[index]>=max){
            return null;
        }
        TreeNode n = new TreeNode(preorder[index]);
        index++;
        n.left = getBST(preorder, min, n.val);
        n.right = getBST(preorder, n.val, max);
        return n;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
