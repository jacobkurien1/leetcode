package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
https://leetcode.com/problems/find-largest-value-in-each-tree-row/
You need to find the largest value in each row of a binary tree.

Example:
Input:

          1
         / \
        3   2
       / \   \
      5   3   9

Output: [1, 3, 9]
 */
/*
Running time is O(n)
Space needed O(n)
 */
public class LargestValuesInTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if(root == null){
            return ret;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int maxValue =Integer.MIN_VALUE;
            int qSize = q.size();
            for(int i =0; i<qSize; i++){
                TreeNode n = q.poll();
                maxValue = Math.max(n.val, maxValue);
                if(n.left != null){
                    q.add(n.left);
                }
                if(n.right!= null){
                    q.add(n.right);
                }
            }
            ret.add(maxValue);
        }
        return ret;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
