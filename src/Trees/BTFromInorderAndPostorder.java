package Trees;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
 */
/*
Running time is O(n)
Space needed is O(n)
 */
public class BTFromInorderAndPostorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length!= postorder.length || inorder.length == 0){
            return null;
        }
        Map<Integer, Integer> iIndexMap = new HashMap<>();
        for(int i = 0; i<inorder.length; i++){
            iIndexMap.put(inorder[i], i);
        }
        return buildTreeUtil(inorder, postorder, iIndexMap,
                0, inorder.length-1, 0, postorder.length-1);
    }

    TreeNode buildTreeUtil(int[] inorder, int[] postorder, Map<Integer, Integer>iIndexMap,
                           int iSt, int iEnd, int pSt, int pEnd){
        if(pEnd<0 || pSt>=postorder.length || iSt<0 || iEnd>=inorder.length ||
                pEnd<pSt || iEnd<iSt){
            return null;
        }
        TreeNode n = new TreeNode(postorder[pEnd]);
        int inorderIndex = iIndexMap.getOrDefault(n.val, -1);
        if(inorderIndex == -1){
            return null; //maybe throw here
        }
        n.right = buildTreeUtil(inorder, postorder, iIndexMap,
                inorderIndex+1, iEnd, pEnd - (iEnd - inorderIndex), pEnd-1);
        n.left = buildTreeUtil(inorder, postorder, iIndexMap,
                iSt, inorderIndex-1, pSt, pSt + (inorderIndex-1-iSt));
        return n;

    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
