package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/closest-leaf-in-a-binary-tree/
Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.

Example 1:

Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
          1
         / \
        3   2

Output: 2 (or 3)

Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
Example 2:

Input:
root = [1], k = 1
Output: 1

Explanation: The nearest leaf node is the root node itself.
Example 3:

Input:
root = [1,2,3,4,null,null,null,5,null,6], k = 2
Diagram of binary tree:
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6

Output: 3
Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
Note:
root represents a binary tree with at least 1 node and at most 1000 nodes.
Every node has a unique node.val in range [1, 1000].
There exists some node in the given binary tree for which node.val == k.
 */
/*
We do a BFS here and also get the hashmap to get the parent node.
Running time is O(N)
Space needed is O(N)
 */
public class ClosestLeafToANode {
    HashMap<Integer, TreeNode> parentMap = new HashMap<>();
    public int findClosestLeaf(TreeNode root, int k) {
        TreeNode kNode = findK(root, k);
        if(kNode == null){
            return -1;
        }
        populateParent(root, null);
        return closestLeaf(kNode);
    }

    int closestLeaf(TreeNode n){
        Queue<TreeNode> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        q.add(n);
        visited.add(n.val);
        while(!q.isEmpty()){
            TreeNode curr = q.poll();
            if(curr.left == null && curr.right == null){
                return curr.val;
            }
            if(curr.left != null && !visited.contains(curr.left.val)){
                visited.add(curr.left.val);
                q.add(curr.left);
            }
            if(curr.right != null && !visited.contains(curr.right.val)){
                visited.add(curr.right.val);
                q.add(curr.right);
            }
            TreeNode parent = parentMap.get(curr.val);
            if(parent!=null && !visited.contains(parent.val)){
                visited.add(parent.val);
                q.add(parent);
            }
        }
        return -1;
    }

    TreeNode findK(TreeNode n, int k){
        if(n == null){
            return null;
        }
        if(n.val == k){
            return n;
        }
        TreeNode left = findK(n.left, k);
        if(left != null){
            return left;
        }
        return findK(n.right, k);
    }

    void populateParent(TreeNode n, TreeNode parent){
        if(n==null){
            return;
        }
        parentMap.put(n.val, parent);
        populateParent(n.left, n);
        populateParent(n.right, n);
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
