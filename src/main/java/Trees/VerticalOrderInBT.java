package Trees;

import java.util.*;

/*
https://leetcode.com/problems/binary-tree-vertical-order-traversal/
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
 */
/*
Running time is O(n)
Space needed is O(n)
 */
public class VerticalOrderInBT {
    HashMap<Integer, List<Integer>> hm;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        hm = new HashMap<>();
        levelOrder(root);
        List<List<Integer>> ret = new ArrayList<>();
        if(!hm.isEmpty()){
            for(int i = min; i<=max; i++){
                ret.add(hm.get(i));
            }
        }
        return ret;
    }

    void levelOrder(TreeNode root){
        if(root == null){
            return;
        }
        Deque<TreeNode> q = new LinkedList<TreeNode>();
        HashMap<TreeNode, Integer> colMap = new HashMap<>();
        q.addFirst(root);
        colMap.put(root, 0);
        while(!q.isEmpty()){
            TreeNode n = q.pollLast();
            int col = colMap.get(n);
            List<Integer> curColList = hm.getOrDefault(col, new ArrayList<Integer>());
            curColList.add(n.val);
            hm.put(col, curColList);

            min = Math.min(min, col);
            max = Math.max(max, col);
            if(n.left != null){
                q.addFirst(n.left);
                colMap.put(n.left, col-1);
            }
            if(n.right!= null){
                q.addFirst(n.right);
                colMap.put(n.right, col+1);
            }
        }
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
