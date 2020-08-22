package Trees;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/serialize-and-deserialize-bst/
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
/*
Running time is O(N)
Space needed is O(N)
 */
public class SerializeAndDeserializeBST {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        if(sb.length() != 0) {
            sb.setLength(sb.length()-1);
        }
        return sb.toString();
    }

    void preOrder(TreeNode n, StringBuilder sb){
        if(n == null){
            return;
        }
        sb.append(Integer.toString(n.val) + ",");
        preOrder(n.left, sb);
        preOrder(n.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length()==0){
            return null;
        }
        String[] parts = data.split(",");
        Queue<Integer> q = new LinkedList<Integer>();
        for(String part: parts){
            q.add(Integer.parseInt(part));
        }
        return createPreOrder(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    TreeNode createPreOrder(Queue<Integer> q, int min, int max){
        if(q.isEmpty() || q.peek()>max || q.peek()<min){
            return null;
        }
        TreeNode n = new TreeNode(q.poll());
        n.left = createPreOrder(q, min, n.val);
        n.right = createPreOrder(q, n.val, max);
        return n;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
