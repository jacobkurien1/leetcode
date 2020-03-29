package DynamicProgramming;

/*
https://leetcode.com/problems/wildcard-matching/
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false
 */
/*
Use Dynamic programming or recursion with memoization
Running time is O(stringLength*PatternLength)
Space used is O(stringLength)
 */
public class WildCardMatching {
    public boolean isMatch(String s, String p) {
        if(s.length() == 0 && p.length()==0){
            return true;
        } else if(p.length() == 0){
            return false;
        }
        boolean[] prev = new boolean[s.length()+1];
        prev[0] = true;

        for( int pIndex = 0; pIndex<p.length(); pIndex++){
            boolean[] curr = new boolean[s.length()+1];
            boolean isAnyCurrMatch = false;
            for(int sIndex = 0; sIndex<=s.length(); sIndex++){
                if(sIndex == 0){
                    curr[0] = (p.charAt(pIndex) == '*')?prev[0]:false;
                } else {
                    if(p.charAt(pIndex) == '*'){
                        curr[sIndex] = curr[sIndex-1] | prev[sIndex];
                    } else if (p.charAt(pIndex) == s.charAt(sIndex-1) || p.charAt(pIndex) == '?'){
                        curr[sIndex] = prev[sIndex-1];
                    } else {
                        curr[sIndex] = false;
                    }
                }
                isAnyCurrMatch |= curr[sIndex];
            }
            prev = curr;
            if(!isAnyCurrMatch){
                return false;
            }
        }
        return prev[s.length()];
    }
}
