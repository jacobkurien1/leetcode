package Trees;

/*
https://leetcode.com/problems/house-robber-iii/
The thief has found himself a new place for his thievery again.
There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house.
After a tour, the smart thief realized that "all houses in this place forms a binary tree".
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \
     3   1

Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:

Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
/*
Running time is O(n)
Space is O(n) for recursively checking for maximum bounty
 */
public class HouseRobberNoStealingInNodeAndChildrenTogether {
    public int rob(TreeNode root) {
        int[] rob = robUtil(root);
        return Math.max(rob[0], rob[1]);
    }

    int[] robUtil(TreeNode n){
        if(n == null){
            return new int[2];
        }
        int[] left = robUtil(n.left);
        int[] right = robUtil(n.right);
        int[] ret = new int[2];
        ret[0] = n.val + left[1] + right[1]; // 0th element is the max with n.val (Take n)
        // 1st element is the max value seen till now without taking n.val (Dont take n)
        ret[1] = Math.max(left[0] , left[1])+ Math.max(right[0] , right[1]);
        return ret;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
