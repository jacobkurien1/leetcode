package Trees;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/n-ary-tree-level-order-traversal/
Given an n-ary tree, return the level order traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal,
each group of children is separated by the null value (See examples).
Example 1:
Input: root = [1,null,3,2,4,null,5,6]
Output: [[1],[3,2,4],[5,6]]

Example 2:
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]


Constraints:

The height of the n-ary tree is less than or equal to 1000
The total number of nodes is between [0, 10^4]

 */
/*
Running time is O(n)
Space is O(n)
 */
public class LevelOrderNaryTree {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levels = new ArrayList<>();
        if(root == null){
            return levels;
        }

        List<Node> currLevel = new ArrayList<>();
        currLevel.add(root);

        while(!currLevel.isEmpty()){
            List<Node> nextLevel = new ArrayList<>();
            List<Integer> level = new ArrayList<>();
            for(Node n:currLevel){
                level.add(n.val);
                for(Node child : n.children){
                    nextLevel.add(child);
                }
            }
            currLevel = nextLevel;
            levels.add(level);
        }
        return levels;
    }

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
    }
}
