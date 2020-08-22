package Trees;

/*
https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in its subtree.



Example 1:

Input: [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation:



We return the node with value 2, colored in yellow in the diagram.
The nodes colored in blue are the deepest nodes of the tree.
The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
Both the input and output have TreeNode type.


Note:

The number of nodes in the tree will be between 1 and 500.
The values of each node are unique.
 */
/*
Send the max depth at any node and whenever we have same max depth in left and right child that node is the maxdepthLCA.
Running time is O(n)
Space needed is O(n) for a recursion of a skewed tree
 */
public class LCAWithAllDeepestNodes {
    TreeNode deepestRoot  = null;
    int maxDepth = 0;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        deepestRoot = root;
        findDepth(root, 0);
        return deepestRoot;
    }

    int findDepth(TreeNode n, int depth){
        if(n.left == null && n.right == null){
            if(depth > maxDepth){
                deepestRoot = n;
                maxDepth = depth;
            }
            return depth;
        }
        int left = (n.left == null)?0:findDepth(n.left, depth+1);
        int right = (n.right == null)?0:findDepth(n.right, depth+1);
        if(left == right && left == maxDepth){
            deepestRoot = n;
        }
        return Math.max(left, right);
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
