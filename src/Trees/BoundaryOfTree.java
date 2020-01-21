package Trees;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/boundary-of-binary-tree
Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root.
Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
(The values of the nodes may still be duplicates.)

Left boundary is defined as the path from root to the left-most node.
Right boundary is defined as the path from root to the right-most node.
If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary.
Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists.
If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1

Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].


Example 2

Input:
    ____1_____
   /          \
  2            3
 / \          /
4   5        6
   / \      / \
  7   8    9  10

Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 */
/*
Running time is O(n)
Space is O(n) for a skewed tree
 */
public class BoundaryOfTree {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer>  boundary = new ArrayList<>();
        preOrder(root, boundary, true, false, false);
        return boundary;
    }

    void preOrder(TreeNode n, List<Integer> boundary, boolean isRoot, boolean isLeftBoundary, boolean isRightBoundary){
        if(n == null){
            return;
        }
        if(n.left == null && n.right == null){
            // leaf node;
            boundary.add(n.val);
            return;
        }
        if(isRoot){
            boundary.add(n.val);
            preOrder(n.left, boundary, false, true, false);
            preOrder(n.right, boundary, false, false, true);
        } else {
            if(isLeftBoundary){
                boundary.add(n.val);
            }
            preOrder(n.left, boundary, false, isLeftBoundary, isRightBoundary && n.right == null);
            preOrder(n.right, boundary, false, isLeftBoundary && n.left==null, isRightBoundary);
            if(isRightBoundary){
                boundary.add(n.val);
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
