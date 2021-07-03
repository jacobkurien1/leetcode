package main.java.BinaryTreeProblems;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/maximum-width-of-binary-tree/
Given a binary tree, write a function to get the maximum width of the given tree.
The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level,
where the null nodes between the end-nodes are also counted into the length calculation.

It is guaranteed that the answer will in the range of 32-bit signed integer.

Example 1:

Input:

           1
         /   \
        3     2
       / \     \
      5   3     9

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:

Input:

          1
         /
        3
       / \
      5   3

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:

Input:

          1
         / \
        3   2
       /
      5

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:

Input:

          1
         / \
        3   2
       /     \
      5       9
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Constraints:

The given binary tree will have between 1 and 3000 nodes.
 */
/*
Running time is O(n)
Space needed is O(n)
 */
public class WidthOfBT {
    public int widthOfBinaryTree(TreeNode root) {
        int maxWidth =0;
        Queue<Pair<Integer, TreeNode>> q = new LinkedList<>();
        q.add(new Pair(0, root));
        while(!q.isEmpty()){
            int qSize = q.size();
            int minIndex = 0;
            int maxIndex = 0;
            for(int i =0; i<qSize; i++){
                Pair<Integer, TreeNode> n = q.poll();
                if (i == 0){
                    minIndex = n.getKey();
                }
                if(i == qSize -1){
                    maxIndex = n.getKey();
                }
                if(n.getValue().left!= null){
                    q.add(new Pair(n.getKey() * 2, n.getValue().left));
                }
                if(n.getValue().right!=null){
                    q.add(new Pair(n.getKey() * 2 + 1, n.getValue().right));
                }
            }
            maxWidth = Math.max(maxWidth, maxIndex - minIndex + 1);
        }
        return maxWidth;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
