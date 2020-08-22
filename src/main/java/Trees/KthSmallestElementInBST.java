package Trees;
/*
https://leetcode.com/problems/kth-smallest-element-in-a-bst/
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.



Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?



Constraints:

The number of elements of the BST is between 1 to 10^4.
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 */
/*
Running time is O(Math.min(K, N)) where N is the total number of nodes.
Space needed is O(N) in worst case for a skewed tree
 */
public class KthSmallestElementInBST {
    int order = 0;
    public int kthSmallest(TreeNode root, int k) {
        //Assumption: K is valid
        return inOrderReturnAtK(root, k).val;
    }

    TreeNode inOrderReturnAtK(TreeNode n, int k){
        if(n == null){
            return null;
        }
        TreeNode left = inOrderReturnAtK(n.left, k);
        if(left != null){
            return left;
        }
        if(++order == k){
            return n;
        }
        return inOrderReturnAtK(n.right, k);
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
