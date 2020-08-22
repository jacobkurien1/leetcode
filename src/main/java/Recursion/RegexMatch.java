package Recursion;
/*
https://leetcode.com/problems/regular-expression-matching/
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
 */
public class RegexMatch {
    public boolean isMatch(String s, String p) {
        return matchUtil(s, 0, p, 0);
    }

    boolean matchUtil(String s, int sIndex, String p, int pIndex){
        if(sIndex == s.length() && pIndex == p.length()){
            return true;
        }
        if(pIndex == p.length()){
            return false;
        }

        if(pIndex +1<p.length() && p.charAt(pIndex+1) == '*'){
            if(matchUtil(s, sIndex, p, pIndex+2)){
                return true;
            }
            while(sIndex<s.length() && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.')){
                if(matchUtil(s, ++sIndex, p, pIndex+2)){
                    return true;
                }
            }
            return false;
        }
        if(sIndex < s.length() && s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.'){
            return matchUtil(s, sIndex+1, p, pIndex+1);
        }
        return false;
    }
}
