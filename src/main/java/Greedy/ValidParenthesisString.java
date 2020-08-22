package Greedy;

/*
https://leetcode.com/problems/valid-parenthesis-string/
Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid.
We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].
 */
/*
Keep track of the max and min openMinusClose and return false if min goes negative during a parenthesis close.
if the min at end is 0, then return true.

Running time is O(n)
Space needed is O(1)
 */
public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        if(s.length() == 0){
            return true;
        }
        int openMinusCloseMin = 0;
        int openMinusCloseMax = 0;
        for(char c :s.toCharArray()){
            if(c == '('){
                openMinusCloseMin++;
                openMinusCloseMax++;
            } else if (c == ')'){
                openMinusCloseMin--;
                openMinusCloseMax--;
                if(openMinusCloseMax<0){
                    return false;
                }
            } else {
                openMinusCloseMin--;
                openMinusCloseMax++;
            }
            openMinusCloseMin = Math.max(0, openMinusCloseMin);
        }
        return openMinusCloseMin == 0;
    }
}
