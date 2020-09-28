package main.java.Trees;

import java.util.HashMap;
/*
https://leetcode.com/problems/path-sum-iii/
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
 */
/*
Here we keep storing the prefix sum of a path in a hashmap.
Running time is O(n)
Space is O(n) for the hashmap
 */
public class PathSumInATree {
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> cummSumFreq = new HashMap<>();
        cummSumFreq.put(0,1);
        return pathSumCount(root, sum, 0, cummSumFreq);
    }

    int pathSumCount(TreeNode n, int sum, int currSum, HashMap<Integer, Integer> cummSumFreq){
        if(n == null){
            return 0;
        }
        currSum += n.val;
        int sumFreq = cummSumFreq.getOrDefault(currSum - sum, 0);;
        cummSumFreq.put(currSum, cummSumFreq.getOrDefault(currSum, 0)+1);
        int left = pathSumCount(n.left, sum, currSum, cummSumFreq);
        int right = pathSumCount(n.right, sum, currSum, cummSumFreq);
        cummSumFreq.put(currSum, cummSumFreq.get(currSum)-1); // equivalent to decrement by 1
        return sumFreq+left+right;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
