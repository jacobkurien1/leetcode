package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
https://leetcode.com/problems/binary-tree-right-side-view/
Given a binary tree, imagine yourself standing on the right side of it,
return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
 */
/*
Use a Queue and get its size and keep adding the first element in the level
Running time O(n)
Space O(n) Because breadth-first search visits the tree layer-by-layer,
the queue will be at its largest immediately before visiting the largest layer.
The size of this layer is 0.5n = O(n)0.5n=O(n) in the worst case (a complete binary tree).
 */
public class RightSideViewForBT {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if(root == null){
            return ret;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size; i++){
                TreeNode n = q.poll();
                if(i==0){
                    ret.add(n.val);
                }
                if(n.right!=null){
                    q.add(n.right);
                }
                if(n.left != null){
                    q.add(n.left);
                }
            }
        }
        return ret;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
