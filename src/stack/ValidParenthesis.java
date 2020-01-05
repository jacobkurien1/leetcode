package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
https://leetcode.com/problems/valid-parentheses/
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
 */
public class ValidParenthesis {
    private final Map<Character, Character> parenthesisMap = new HashMap<Character, Character>();

    public ValidParenthesis(){
        parenthesisMap.put('(', ')');
        parenthesisMap.put('{', '}');
        parenthesisMap.put('[', ']');
    }
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<Character>();
        for(int i = 0; i<s.length(); i++){
            if(parenthesisMap.containsKey(s.charAt(i))){
                st.push(s.charAt(i));
            } else{
                if(st.isEmpty() || s.charAt(i) != parenthesisMap.get(st.pop())){
                    return false;
                }
            }
        }
        return st.isEmpty();
    }
}
