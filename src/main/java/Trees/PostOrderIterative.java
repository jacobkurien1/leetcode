package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
https://leetcode.com/problems/binary-tree-postorder-traversal
Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
Follow up: Recursive solution is trivial, could you do it iteratively?
 */
/*
Running time is O(n)
Space needed is O(n) for a skewed tree
 */
public class PostOrderIterative {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postOrder = new ArrayList<>();
        if(root == null){
            return postOrder;
        }
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        TreeNode curr = null;
        while(!st.isEmpty()){
            while(st.peek().left != null && (curr == null || (curr != st.peek().left && curr != st.peek().right))){
                st.push(st.peek().left);
            }
            if(st.peek().right!=null && (curr == null || st.peek().right != curr)){
                st.push(st.peek().right);
            } else {
                curr = st.pop();
                postOrder.add(curr.val);
            }
        }
        return postOrder;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /*
    Algo 2: do preorder and manipulate the way output is stored
     */
    public List<Integer> postorderTraversalAlgo2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.addFirst(node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return output;
    }
}
