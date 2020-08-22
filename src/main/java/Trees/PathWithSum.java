package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
https://leetcode.com/problems/path-sum-ii/
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
 */
/*
Running time is n log(n), where we can have n/2 possible paths and n/2 work of appending to list at each of log(n) level
Space needed is O(n log(n)) to save all the n/2 possible paths of length log(n)
 */
public class PathWithSum {

    List<List<Integer>> getPathsWithSum (TreeNode n, int sum){
        if (n== null){
            return new ArrayList<>();
        } else if(n.left== null && n.right == null && sum - n.val == 0){
            List<List<Integer>> ret = new ArrayList<>();
            ret.add(new LinkedList<Integer>(){
                {
                    add(n.val);
                }
            });
            return ret;
        }

        List<List<Integer>> left = getPathsWithSum(n.left, sum - n.val);
        List<List<Integer>> right = getPathsWithSum(n.right, sum - n.val);
        List<List<Integer>> merge= new ArrayList<>();
        mergeLists(merge, left, n.val);
        mergeLists(merge, right, n.val);
        return merge;
    }

    void mergeLists(List<List<Integer>> ip, List<List<Integer>> toBeMergedList, int valToAdd){
        for(List<Integer> toBeMerged : toBeMergedList ){
            toBeMerged.add(0, valToAdd);
            ip.add(toBeMerged);
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
