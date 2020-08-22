package Trees;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/subtree-of-another-tree/
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s.
A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
 */
/*
Running time is O(n)
Space needed is O(1)
 */
public class SubTreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        List<TreeNode> possibleEquals = new ArrayList<>();
        nodeWithEqVal(s, t, possibleEquals);
        for(TreeNode n: possibleEquals){
            if(subTreeUtil(n, t)){
                return true;
            }
        }
        return false;
    }

    void nodeWithEqVal(TreeNode s, TreeNode t, List<TreeNode> possibleEquals){
        if(s== null){
            return;
        }
        if(s.val == t.val){
            possibleEquals.add(s);
        }
        nodeWithEqVal(s.left, t, possibleEquals);
        nodeWithEqVal(s.right, t, possibleEquals);
    }

    boolean subTreeUtil(TreeNode n1, TreeNode n2){
        if(n1 == null && n2 == null){
            return true;
        }
        if(n1 == null || n2 == null || n1.val != n2.val){
            return false;
        }
        return subTreeUtil(n1.left, n2.left) && subTreeUtil(n1.right, n2.right);
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
