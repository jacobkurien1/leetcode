package StringProblems;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/longest-substring-without-repeating-characters/
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> charSet = new HashSet<Character>();
        int st = 0,maxSubstring = 0;
        for(int end = 0; end<s.length(); end++){
            if(charSet.contains(s.charAt(end))){
                maxSubstring = Math.max(maxSubstring, end-st);
                while(s.charAt(st)!=s.charAt(end)){
                    charSet.remove(s.charAt(st++));
                }
                st++;
            } else {
                charSet.add(s.charAt(end));
            }
        }

        return Math.max(maxSubstring, s.length()-st);
    }
}
