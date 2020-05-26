package Trees;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/check-completeness-of-a-binary-tree/
Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible.
It can have between 1 and 2h nodes inclusive at the last level h.



Example 1:



Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
Example 2:



Input: [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.

Note:

The tree will have between 1 and 100 nodes.
 */
/*
Running time is O(n)
Space needed is O(n)
 */
public class BTCompleteness {
    public boolean isCompleteTree(TreeNode root) {
        if(root == null){
            return false;
        }
        boolean hasSeenLess = false;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int qSize = q.size();
            for(int i = 0; i<qSize; i++){
                TreeNode n  = q.poll();
                if(n.left != null){
                    if(hasSeenLess){
                        return false;
                    }
                    q.add(n.left);
                } else {
                    hasSeenLess = true;
                }
                if(n.right != null){
                    if(hasSeenLess){
                        return false;
                    }
                    q.add(n.right);
                } else {
                    hasSeenLess = true;
                }
            }
        }
        return true;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
