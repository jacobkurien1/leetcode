package Trees;

import java.util.*;

/*
https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.



Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation:
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.


Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.
 */
/*
Running time is O(N)
Space needed is O(N)
 */
public class NodesAtDistanceK {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        HashMap<TreeNode, TreeNode> parents = new HashMap<>();
        buildParent(root, null, parents);

        //Now do bfs
        Queue<TreeNode> level = new LinkedList<>();
        level.add(target);
        HashSet<TreeNode> hs = new HashSet<>();
        hs.add(target);
        while(!level.isEmpty() && k != 0){
            int levelSize = level.size();
            for(int i = 0; i<levelSize; i++){
                TreeNode n = level.poll();
                if(n.left != null && !hs.contains(n.left)){
                    level.add(n.left);
                    hs.add(n.left);
                }
                if(n.right != null && !hs.contains(n.right)){
                    level.add(n.right);
                    hs.add(n.right);
                }
                TreeNode nParent = parents.get(n);
                if(nParent != null && !hs.contains(nParent)){
                    level.add(nParent);
                    hs.add(nParent);
                }
            }
            k--;
        }
        List<Integer> distanceOfK = new ArrayList<>();
        if(k!= 0){
            return distanceOfK;
        }
        while(!level.isEmpty()){
            distanceOfK.add(level.poll().val);
        }
        return distanceOfK;
    }

    void buildParent(TreeNode n, TreeNode parent, HashMap<TreeNode, TreeNode> parents){
        if(n == null){
            return;
        }
        parents.put(n, parent);
        buildParent(n.left, n, parents);
        buildParent(n.right, n, parents);
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
