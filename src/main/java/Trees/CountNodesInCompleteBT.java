package Trees;

/*
https://leetcode.com/problems/count-complete-tree-nodes/
Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled,
and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input:
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
 */
/*
Running time is O(logn)
Space needed is O(1)
 */
public class CountNodesInCompleteBT {
    public int countNodes(TreeNode root) {
        TreeNode curr = root;
        int height = getHeight(root);
        int nodeCount = (int)Math.pow(2, height-1) -1;//count of non-leaf nodes
        int left = 0;
        int right = (int)Math.pow(2, height-1) -1;
        int leafTill = 0;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(isNodePresent(mid, height-1, root)){
                leafTill = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return nodeCount + leafTill+1;
    }

    boolean isNodePresent(int num, int depth, TreeNode curr){
        char[] binary = new char[depth];
        for(int i = 0; i<depth; i++){
            binary[i] = (num %2 == 0)?'0':'1';
            num = num/2;
        }

        for(int i=depth-1; i>=0; i--){
            if(binary[i] == '1'){
                curr = curr.right;
            } else {
                curr = curr.left;
            }
            if(curr == null){
                return false;
            }
        }
        return true;
    }

    int getHeight(TreeNode n){
        int height = 0;
        while(n!=null){
            height++;
            n = n.left;
        }
        return height;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
