package Trees;

/*
https://leetcode.com/problems/largest-bst-subtree/
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

Example:

Input: [10,5,15,1,8,null,7]

   10
   / \
  5  15
 / \   \
1   8   7

Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one.
             The return value is the subtree's size, which is 3.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
 */
/*
Running time is O(n)
Space needed is O(n) for skewed trees
 */
public class LargestSubtreeWhichIsBST {
    public int largestBSTSubtree(TreeNode root) {
        if(root == null){
            return 0;
        }
        getMaxMinCount(root);
        return maxCountBSTSubtree;
    }
    int maxCountBSTSubtree = 0;

    MaxMinCount getMaxMinCount(TreeNode n){
        if(n == null){
            return null;
        }
        MaxMinCount left = getMaxMinCount(n.left);
        MaxMinCount right = getMaxMinCount(n.right);
        if((left == null || n.val > left.max) && (right == null || n.val < right.min)){
            int leftCount = (left ==null)?0:left.count;
            int rightCount = (right == null)?0:right.count;
            int currentCount = leftCount+rightCount +1;
            maxCountBSTSubtree = Math.max(maxCountBSTSubtree, currentCount);
            return new MaxMinCount(((left == null)?n.val:left.min),
                    ((right == null)?n.val:right.max),
                    currentCount);
        }
        return new MaxMinCount();
    }

    class MaxMinCount {
        int min;
        int max;
        int count;
        MaxMinCount(){
            min = Integer.MIN_VALUE;
            max = Integer.MAX_VALUE;
            count = 0;
        }
        MaxMinCount(int min, int max, int count){
            this.min = min;
            this.max = max;
            this.count = count;
        }
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
