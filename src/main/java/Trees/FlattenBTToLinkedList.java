package Trees;
/*
https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
 */
/*
Running time is O(n)
Space is O(n) for a skewed BT.
 */
public class FlattenBTToLinkedList {
    public void flatten(TreeNode root) {
        if(root != null){
            getList(root);
        }
    }

    LList getList(TreeNode n){
        if(n.left == null && n.right == null){
            return new LList(n, n);
        }
        LList l = null;
        LList r = null;
        if(n.left != null){
            l = getList(n.left);
            n.left = null;
        }
        if(n.right != null){
            r = getList(n.right);
        }
        TreeNode curr = n;
        if(l != null){
            curr.right  = l.st;
            curr = l.end;
        }
        if(r != null){
            curr. right = r.st;
            curr = r.end;
        }
        return new LList(n, curr);
    }

    class LList{
        TreeNode st;
        TreeNode end;
        LList(TreeNode st, TreeNode end){
            this.st = st;
            this.end = end;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
