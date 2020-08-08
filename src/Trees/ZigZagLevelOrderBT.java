package Trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
Given a binary tree, return the zigzag level order traversal of its nodes' values.
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 */
/*
Running time is O(n)
Space needed is O(n)
 */
public class ZigZagLevelOrderBT {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null){
            return levels;
        }
        Deque<TreeNode> dqCurr = new LinkedList<>();
        dqCurr.addFirst(root);
        boolean goLeft = true;
        while(!dqCurr.isEmpty()){
            Deque<TreeNode> dqNext = new LinkedList<>(); // Used as stack
            List<Integer> level = new ArrayList<>();
            while(!dqCurr.isEmpty()){
                TreeNode n = dqCurr.pollFirst();
                level.add(n.val);
                if(goLeft){
                    if(n.left != null){
                        dqNext.addFirst(n.left);
                    }
                    if(n.right!=null){
                        dqNext.addFirst(n.right);
                    }
                }else{
                    if(n.right!=null){
                        dqNext.addFirst(n.right);
                    }
                    if(n.left != null){
                        dqNext.addFirst(n.left);
                    }
                }
            }
            goLeft = !goLeft;
            dqCurr = dqNext;
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
