package Trees;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/binary-tree-level-order-traversal/
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 */
/*
Running time is O(n)
Space is O(n)
 */
public class LevelOrderBT {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null){
            return levels;
        }
        List<TreeNode> currLevel = new ArrayList<>();
        currLevel.add(root);

        while(!currLevel.isEmpty()){
            List<Integer> level = new ArrayList<>();
            List<TreeNode> nextLevel = new ArrayList<>();
            for(TreeNode n : currLevel){
                level.add(n.val);
                if(n.left != null){
                    nextLevel.add(n.left);
                }
                if(n.right != null){
                    nextLevel.add(n.right);
                }
            }
            currLevel = nextLevel;
            levels.add(level);
        }
        return levels;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
