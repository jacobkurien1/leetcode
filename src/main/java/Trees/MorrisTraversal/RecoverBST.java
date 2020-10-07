package main.java.Trees.MorrisTraversal;

/*
https://leetcode.com/problems/recover-binary-search-tree/
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
Follow up:

A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?
 */
/*
Running time is O(n)
Space needed is O(1)
 */
public class RecoverBST {
    public void recoverTree(TreeNode root) {
        MinMax minMax = new MinMax();
        TreeNode curr = root;
        TreeNode lastInOrder = null;
        while(curr != null){
            if(curr.left != null){
                TreeNode pred= getPred(curr);
                if(pred.right == null){
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    pred.right = null;
                    if(lastInOrder != null && lastInOrder.val > curr.val){
                        minMax.update(curr, lastInOrder);
                    }
                    lastInOrder = curr;
                    curr = curr.right;
                }
            } else {
                if(lastInOrder != null && lastInOrder.val > curr.val){
                    minMax.update(curr, lastInOrder);
                }
                lastInOrder = curr;
                curr = curr.right;
            }
        }
        swap(minMax);
    }

    TreeNode getPred(TreeNode n){
        TreeNode curr = n.left;
        while(curr.right != null && curr.right != n){
            curr = curr.right;
        }
        return curr;
    }

    void swap(MinMax minMax){
        int temp = minMax.min.val;
        minMax.min.val = minMax.max.val;
        minMax.max.val = temp;
    }

    class MinMax{
        TreeNode min;
        TreeNode max;
        public MinMax(){
            this.min = new TreeNode(Integer.MAX_VALUE);
            this.max = new TreeNode(Integer.MIN_VALUE);
        }
        public MinMax(TreeNode min, TreeNode max){
            this.min = min;
            this.max = max;
        }

        void update(TreeNode min, TreeNode max){
            if(min.val<this.min.val){
                this.min = min;
            }
            if(max.val>this.max.val){
                this.max = max;
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
