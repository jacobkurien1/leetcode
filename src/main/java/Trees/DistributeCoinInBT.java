package Trees;

/*
https://leetcode.com/problems/distribute-coins-in-binary-tree/
Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.



Example 1:



Input: [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
Example 2:



Input: [0,3,0]
Output: 3
Explanation: From the left child of the root, we move two coins to the root [taking two moves].
Then, we move one coin from the root of the tree to the right child.
Example 3:



Input: [1,0,2]
Output: 2
Example 4:



Input: [1,0,0,null,3]
Output: 4


Note:

1<= N <= 100
0 <= node.val <= N
 */
/*
Running time is O(n)
Space needed is O(n) for skewed trees
 */
public class DistributeCoinInBT {
    int moves = 0;
    public int distributeCoins(TreeNode root) {
        balance(root);
        return moves;
    }

    public int balance(TreeNode n){
        if(n == null){
            return 0;
        }
        int l = balance(n.left);
        int r = balance(n.right);
        moves += Math.abs(l) + Math.abs(r);
        return n.val - 1 + l + r;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
