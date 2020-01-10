package BruteForce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/*
https://leetcode.com/problems/remove-invalid-parentheses/
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 */
/*
Running time is O(2^N)
Space Complexity : O(N)O(N) because we are resorting to a recursive solution and for a recursive solution there is always
stack space used as internal function states are saved onto a stack during recursion.
The maximum depth of recursion decides the stack space used. Since we process one character at a time and the base case for
the recursion is when we have processed all of the characters of the expression string, the size of the stack would be O(N)O(N).
Note that we are not considering the space required to store the valid expressions. We only count the intermediate space here.
 */
public class RemoveInvalidParentheses {
    HashSet<String> ret = new HashSet<String>();
    public List<String> removeInvalidParentheses(String s) {
        int openToRemove = 0;
        int closeToRemove = 0;
        int openMinusClose = 0;
        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                openMinusClose++;
            } else if(s.charAt(i) == ')'){
                openMinusClose--;
                if(openMinusClose < 0){
                    closeToRemove++;
                    openMinusClose = 0;
                }
            }
        }
        openToRemove = openMinusClose;
        removeInvalidParenthesesRec(s, openToRemove, closeToRemove, 0, 0);
        return ret.isEmpty()?Arrays.asList(""):new ArrayList<String>(ret);
    }

    void removeInvalidParenthesesRec(String s, int openToRemove, int closeToRemove, int index, int openMinusClose){
        if(openMinusClose < 0){
            return;
        }

        while(index < s.length() && !(s.charAt(index)=='(' || s.charAt(index)==')')){
            index++;
        }
        if(index ==s.length()){
            if(openToRemove == 0 && closeToRemove == 0){
                ret.add(s);
            }
            return;
        }
        //Dont remove at Index
        removeInvalidParenthesesRec(s, openToRemove, closeToRemove, index+1, openMinusClose+(s.charAt(index)=='('?1:-1));
        //Remove at Index
        if(s.charAt(index) == '(' && openToRemove > 0){
            openToRemove-=1;
        } else if(s.charAt(index) == ')' && closeToRemove > 0) {
            closeToRemove-=1;
        } else {
            return;
        }
        s = s.substring(0, index) + (index == s.length()-1?"":s.substring(index+1, s.length()));
        removeInvalidParenthesesRec(s, openToRemove, closeToRemove, index, openMinusClose);
    }
}
