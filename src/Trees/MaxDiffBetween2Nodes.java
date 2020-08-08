package Trees;
/*
https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

(A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)



Example 1:



Input: [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation:
We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.


Note:

The number of nodes in the tree is between 2 and 5000.
Each node will have value between 0 and 100000.
 */
/*
Running time is O(n)
Space needed is O(n) for a skewed tree.
 */
public class MaxDiffBetween2Nodes {
    public int maxAncestorDiff(TreeNode root) {
        getAncestorDiff(root);
        return maxDifference;
    }

    int maxDifference = 0;

    MaxMin getAncestorDiff(TreeNode n){
        if(n == null){
            return new MaxMin(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        MaxMin mmLeft = getAncestorDiff(n.left);
        MaxMin mmRight = getAncestorDiff(n.right);
        int minChild = Math.min(mmLeft.min, mmRight.min);
        int maxChild = Math.max(mmLeft.max, mmRight.max);
        if(minChild != Integer.MAX_VALUE){
            maxDifference = Math.max(maxDifference, Math.abs(n.val-minChild));
        }
        if(maxChild != Integer.MIN_VALUE){
            maxDifference = Math.max(maxDifference, Math.abs(maxChild - n.val));
        }
        return new MaxMin(Math.max(n.val, maxChild), Math.min(n.val, minChild));
    }
    class MaxMin{
        int max;
        int min;
        public MaxMin(int max, int min){
            this.max = max;
            this.min = min;
        }
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
