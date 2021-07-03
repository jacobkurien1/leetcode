package main.java.Trees;

/*
https://leetcode.com/problems/sum-of-nodes-with-even-valued-grandparent/
Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)

If there are no nodes with an even-valued grandparent, return 0.



Example 1:



Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.


Constraints:

The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.
 */
/*
Running time is O(n)
Space needed is O(n) in case of a skewed tree
 */
public class SumInBTIfGrandparentEven {
    public int sumEvenGrandparent(TreeNode root) {
        return sumEvenGrand(root, null, null);
    }

    int sumEvenGrand(TreeNode n, TreeNode parent, TreeNode grand){
        if(n == null){
            return 0;
        }
        int left = sumEvenGrand(n.left, n, parent);
        int right = sumEvenGrand(n.right, n, parent);
        return left + right + (grand != null && grand.val%2 == 0 ? n.val:0);
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
