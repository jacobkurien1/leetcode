package Trees;

import java.util.Optional;

/*
https://leetcode.com/problems/closest-binary-search-tree-value/
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4
 */
/*
Running time is O(n) for a skewed tree
Space is O(n) for a skewed tree
 */
public class ClosestValueInBST {
    Optional<Integer> closestVal = Optional.empty();
    public int closestValue(TreeNode root, double target) {
        // check for root == null
        BstTraversal(root, target);
        return closestVal.get();
    }

    void BstTraversal(TreeNode n, double target){
        if(n==null){
            return;
        }
        if(!closestVal.isPresent() || Math.abs(closestVal.get()-target) > Math.abs(n.val-target)){
            closestVal = Optional.of(n.val);
        }
        if(n.val<target){
            BstTraversal(n.right, target);
        } else {
            BstTraversal(n.left, target);
        }
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
