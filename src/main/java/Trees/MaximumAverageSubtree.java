package main.java.Trees;
/*
https://leetcode.com/problems/maximum-average-subtree/
Given the root of a binary tree, find the maximum average value of any subtree of that tree.

(A subtree of a tree is any node of that tree plus all its descendants.
The average value of a tree is the sum of its values, divided by the number of nodes.)



Example 1:



Input: [5,6,1]
Output: 6.00000
Explanation:
For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
For the node with value = 6 we have an average of 6 / 1 = 6.
For the node with value = 1 we have an average of 1 / 1 = 1.
So the answer is 6 which is the maximum.


Note:

The number of nodes in the tree is between 1 and 5000.
Each node will have a value between 0 and 100000.
Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 */
/*
Running time is O(n)
Space needed is O(n) for a skewed tree
 */
public class MaximumAverageSubtree {
    public double maximumAverageSubtree(TreeNode root) {
        getAvgInfo(root);
        return maxAvgInfo.getAvg();
    }

    AvgInfo maxAvgInfo = new AvgInfo(0,0);

    AvgInfo getAvgInfo(TreeNode n){
        if(n == null){
            return new AvgInfo(0, 0);
        }
        int sum = n.val;
        int count = 1;
        AvgInfo left = getAvgInfo(n.left);
        AvgInfo right = getAvgInfo(n.right);
        AvgInfo curr = new AvgInfo(sum+left.sum+right.sum, count+left.count+right.count);
        if(curr.getAvg()> maxAvgInfo.getAvg()){
            maxAvgInfo = curr;
        }
        return curr;
    }

    class AvgInfo{
        int sum;
        int count;
        public AvgInfo(int sum, int count){
            this.sum = sum;
            this.count = count;
        }
        double getAvg(){
            if(count == 0){
                return 0;
            }
            return (double)sum/count;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
