package Trees;

import java.util.Stack;

/*
https://leetcode.com/problems/binary-search-tree-iterator
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.



Example:



BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false


Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
 */
/*
Running time Amortized = o(1)
Space needed can be O(n) for a skewed Binary search tree
 */
public class BSTIterator {
    Stack<TreeNode> st = new Stack<>();
    public BSTIterator(TreeNode root) {
        while(root!= null){
            st.push(root);
            root = root.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        // Assumption next is only called after checking hasNext()
        TreeNode retNode = st.pop();
        TreeNode curr = retNode.right;
        while(curr!=null){
            st.push(curr);
            curr = curr.left;
        }
        return retNode.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !st.isEmpty();
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
