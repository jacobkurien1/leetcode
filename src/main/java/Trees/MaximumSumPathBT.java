package Trees;

/*
https://leetcode.com/problems/binary-tree-maximum-path-sum
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
 */
/*
Running time is O(n) where n is the number of nodes of the tree
Space is O(n), since in worst case it could be a skewed BT
 */
public class MaximumSumPathBT {
    int maxVal = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root ==null){
            return 0;
        }
        getMaxPath(root);
        return maxVal;
    }

    int getMaxPath(TreeNode n){
        if(n==null){
            return 0;
        }
        int leftMax = getMaxPath(n.left);
        int rightMax = getMaxPath(n.right);
        int maxValNoLooping = n.val;// This means maxValue without looping around node n
        maxValNoLooping = Math.max(maxValNoLooping, leftMax + n.val);
        maxValNoLooping = Math.max(maxValNoLooping, rightMax + n.val);
        maxVal = Math.max(maxVal, leftMax+rightMax+n.val);
        maxVal = Math.max(maxVal, maxValNoLooping);
        return maxValNoLooping;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
