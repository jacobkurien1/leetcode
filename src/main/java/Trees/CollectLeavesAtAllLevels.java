package Trees;

import java.util.ArrayList;
import java.util.List;
/*
https://leetcode.com/problems/find-leaves-of-binary-tree/
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.



Example:

Input: [1,2,3,4,5]

          1
         / \
        2   3
       / \
      4   5

Output: [[4,5,3],[2],[1]]


Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         /
        2


2. Now removing the leaf [2] would result in this tree:

          1


3. Now removing the leaf [1] would result in the empty tree:

          []
 */
/*
Running time is O(n)
space is O(n) for a skewed tree
 */
public class CollectLeavesAtAllLevels {
    public List<List<Integer>> findLeaves(TreeNode root) {
        traverse(root);
        return ret;
    }

    List<List<Integer>> ret = new ArrayList<>();

    int traverse(TreeNode n){
        if(n == null){
            return 0;
        }
        int maxDepth = traverse(n.left);
        maxDepth = Math.max(maxDepth, traverse(n.right));
        if(ret.size()==maxDepth){
            ret.add(new ArrayList<>());
        }
        ret.get(maxDepth).add(n.val);
        return ++maxDepth;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
