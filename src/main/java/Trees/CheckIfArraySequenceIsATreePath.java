package Trees;

/*
Given a binary tree where each path going from the root to any leaf form a valid sequence, check if a given string is a valid sequence in such binary tree.

We get the given string from the concatenation of an array of integers arr and the concatenation of all values of the nodes along a path results in a sequence in the given binary tree.



Example 1:



Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
Output: true
Explanation:
The path 0 -> 1 -> 0 -> 1 is a valid sequence (green color in the figure).
Other valid sequences are:
0 -> 1 -> 1 -> 0
0 -> 0 -> 0
Example 2:



Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
Output: false
Explanation: The path 0 -> 0 -> 1 does not exist, therefore it is not even a sequence.
Example 3:



Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
Output: false
Explanation: The path 0 -> 1 -> 1 is a sequence, but it is not a valid sequence.


Constraints:

1 <= arr.length <= 5000
0 <= arr[i] <= 9
Each node's value is between [0 - 9].
 */
/*
Running time is O(n)
Space needed is O(n) for a skewed tree
 */
public class CheckIfArraySequenceIsATreePath {
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return validSeqRec(root, arr, 0);
    }

    boolean validSeqRec(TreeNode n, int[] arr, int index){

        if(n == null || index >= arr.length || n.val != arr[index]){
            return false;
        }
        if(index == arr.length-1){
            return n.left == null && n.right == null;
        }
        return (n.left != null && validSeqRec(n.left, arr, index+1)) ||
                (n.right != null && validSeqRec(n.right, arr, index+1));
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
