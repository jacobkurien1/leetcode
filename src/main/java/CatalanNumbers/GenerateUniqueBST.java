package main.java.CatalanNumbers;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/unique-binary-search-trees-ii/
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3


Constraints:

0 <= n <= 8
 */
/*
The computation to construct all the possible trees given a node is a Catalan number(Gn).
Since this is done n times, we have the running time as n(Gn)
Running time of the catalan number is O((4^n)/n^(3/2)), hence the running time is O(4^n/n^(1/2))
Space Complexity is also n(Gn) as we keep all the trees and hence the space requirement is O(4^n/n^(1/2))
 */
public class GenerateUniqueBST {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0){
            return new ArrayList<>();
        }
        return getTrees(1, n);
    }

    List<TreeNode> getTrees(int st, int end){
        List<TreeNode> ret = new ArrayList<>();
        if(st == end){
            ret.add(new TreeNode(st));
            return ret;
        } else if (st > end){
            ret.add(null);
            return ret;
        }
        for (int i = st; i<=end; i++){
            List<TreeNode> left = getTrees(st, i-1);
            List<TreeNode> right = getTrees(i+1, end);
            for(TreeNode l: left){
                for(TreeNode r: right){
                    ret.add(new TreeNode(i, l, r));
                }
            }
        }
        return ret;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }
}

