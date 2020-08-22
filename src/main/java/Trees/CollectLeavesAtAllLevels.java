package Trees;

import java.util.ArrayList;
import java.util.HashMap;
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
space is O(n)
 */
public class CollectLeavesAtAllLevels {
    public List<List<Integer>> findLeaves(TreeNode root) {
        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        collectLeaves(root, hm);
        List<List<Integer>> leavesInLevel = new ArrayList<>();
        int level = 1;
        while(hm.getOrDefault(level, null)!=null){
            leavesInLevel.add(hm.get(level));
            level++;
        }
        return leavesInLevel;
    }

    int collectLeaves(TreeNode n, HashMap<Integer, List<Integer>> hm){
        if(n == null){
            return 0;
        }
        int left = collectLeaves(n.left, hm);
        int right = collectLeaves(n.right, hm);
        int whenNodeBecomesLeaf = Math.max(left, right)+1;
        List<Integer> treeLevel = hm.getOrDefault(whenNodeBecomesLeaf, new ArrayList<Integer>());
        treeLevel.add(n.val);
        hm.put(whenNodeBecomesLeaf, treeLevel);
        return whenNodeBecomesLeaf;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
