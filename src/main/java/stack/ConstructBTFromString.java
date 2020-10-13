package main.java.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/*
https://leetcode.com/problems/construct-binary-tree-from-string/
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.



Example 1:


Input: s = "4(2(3)(1))(6(5))"
Output: [4,2,6,3,1,5]
Example 2:

Input: s = "4(2(3)(1))(6(5)(7))"
Output: [4,2,6,3,1,5,7]
Example 3:

Input: s = "-4(2(3)(1))(6(5)(7))"
Output: [-4,2,6,3,1,5,7]


Constraints:

0 <= s.length <= 3 * 104
s consists of digits, '(', ')', and '-' only.
 */
/*
Running time is O(n)
Space time is O(n)
 */
public class ConstructBTFromString {
    public TreeNode str2tree(String s) {
        Deque<TreeNode> st = new ArrayDeque<>();
        index = 0;
        while(index<s.length()){
            if(s.charAt(index) == ')'){
                st.pop();
                index++;
            } else if(s.charAt(index) == '('){
                index++;
            } else {
                int num = getNumber(s);
                TreeNode curr = new TreeNode(num);
                if(!st.isEmpty()){
                    TreeNode parent = st.peek();
                    if(parent.left == null){
                        parent.left = curr;
                    } else {
                        parent.right = curr;
                    }
                }
                st.push(curr);
            }
        }
        return (st.isEmpty()?null:st.pop());
    }

    int getNumber(String s){
        boolean isNegative = false;
        if(s.charAt(index)== '-'){
            isNegative = true;
            index++;
        }
        int val=0;
        while(index<s.length() && s.charAt(index) >= '0' && s.charAt(index)<='9'){
            val = val*10 + (s.charAt(index) - '0');
            index++;
        }
        return (isNegative?-1*val:val);
    }

    int index;

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
