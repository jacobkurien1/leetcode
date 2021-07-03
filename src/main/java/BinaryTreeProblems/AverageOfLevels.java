package main.java.BinaryTreeProblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Given a non-empty binary tree,
return the average value of the nodes on each level in the form of an array.
Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
Note:
The range of node's value is in the range of 32-bit signed integer.
 */
/*
Running time O(n)
Space needed O(n)
 */
public class AverageOfLevels {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> avgs = new ArrayList<>();
        Queue<TreeNode> bstQueue = new LinkedList<>();
        if(root == null){
            return avgs;
        }
        bstQueue.add(root);
        while(!bstQueue.isEmpty()){
            int qSize = bstQueue.size();
            long levelSum = 0;
            for(int i =0; i<qSize; i++){
                TreeNode n = bstQueue.poll();
                levelSum += n.val;
                if(n.left != null){
                    bstQueue.add(n.left);
                }
                if(n.right != null){
                    bstQueue.add(n.right);
                }
            }
            avgs.add(levelSum/(double)qSize);
        }
        return avgs;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
