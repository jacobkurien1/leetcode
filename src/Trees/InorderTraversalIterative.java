package Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
https://leetcode.com/problems/binary-tree-inorder-traversal/
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?
 */
/*
Running time is O(n)
Space is O(n) for skewed trees
 */
public class InorderTraversalIterative {
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> st = new Stack<TreeNode>();
        List<Integer> inOrder = new ArrayList<>();
        TreeNode curr = root;
        while(curr!= null || !st.isEmpty()){
            while(curr != null){
                st.push(curr);
                curr = curr.left;
            }
            if(!st.isEmpty()){
                curr = st.pop();
                inOrder.add(curr.val);
                curr = curr.right;
            }
        }
        return inOrder;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
