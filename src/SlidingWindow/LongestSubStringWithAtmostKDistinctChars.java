package SlidingWindow;

import java.util.HashMap;

/*
https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.
 */
/*
Running time is O(n)
Space needed is O(n)Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.
 */
public class LongestSubStringWithAtmostKDistinctChars {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s.length() == 0 || k ==0){
            return 0;
        }
        HashMap<Character, Integer> hm = new HashMap<>();
        int st = 0; int end = 0;
        int distinctChars = 0;
        int maxLength = 0;
        while(end<s.length()){
            if(distinctChars<=k){
                int freq = hm.getOrDefault(s.charAt(end), 0);
                if(freq == 0){
                    distinctChars++;
                }
                hm.put(s.charAt(end), freq+1);
                end++;
                if(distinctChars<=k){
                    maxLength = Math.max(maxLength, end-st);
                }
            } else {
                int freq = hm.getOrDefault(s.charAt(st), 0);
                if(freq==1){
                    distinctChars--;
                }
                hm.put(s.charAt(st), freq-1);
                st++;
            }
        }
        return maxLength;
    }
}
