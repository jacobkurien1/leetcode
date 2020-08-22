package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children.
There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree



as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.

Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated by the null value.



For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].

You do not necessarily need to follow the above suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.



Constraints:

The height of the n-ary tree is less than or equal to 1000
The total number of nodes is between [0, 10^4]
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 */
/*
Running time is O(N)
Space needed is O(N)
 */
public class SerializeDeserializeNaryTree {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        serialRecur(root, sb);
        return sb.substring(0, sb.length()-1);
    }

    void serialRecur(Node n, StringBuilder sb){
        sb.append(Integer.toString(n.val)+",");
        for(Node child: n.children){
            serialRecur(child, sb);
        }
        sb.append("#,");
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data == ""){
            return null;
        }
        String[] parts = data.split(",");
        Node root = null;
        Stack<Node> st = new Stack<>();
        for(String part:parts){
            if(part.equals("#")){
                root = st.pop();
            } else {
                Node n = new Node(Integer.parseInt(part), new ArrayList<Node>());
                if(!st.isEmpty()){
                    st.peek().children.add(n);
                }
                st.push(n);
            }
        }
        return root;
    }
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
