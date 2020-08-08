package Trees;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/binary-tree-paths/
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
/*
Running time is O(n)
Space is O(n) if the space for the output is not considered
 */
public class AllPathsInBT {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> allPaths = new ArrayList<>();
        path(root, new StringBuilder(), allPaths);
        return allPaths;
    }

    void path(TreeNode n, StringBuilder sb, List<String> allPaths){
        if(n == null){
            return;
        }
        String arrow = (sb.length()==0?"":"->");
        String nodeVal = Integer.toString(n.val);
        sb.append(arrow + nodeVal);
        if(n.left == null && n.right == null){
            allPaths.add(sb.toString());
        } else {
            path(n.left, sb, allPaths);
            path(n.right, sb, allPaths);
        }
        sb.setLength(sb.length()-(nodeVal.length()+arrow.length()));
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
