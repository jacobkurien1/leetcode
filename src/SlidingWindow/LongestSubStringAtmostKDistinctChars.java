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
Space needed is O(n)
 */
public class LongestSubStringAtmostKDistinctChars {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k == 0 || s== null || s.length()== 0){
            return 0;
        }
        HashMap<Character, Integer> charsMap = new HashMap<>();
        int st = 0; int end  = 0;
        int distinctElem = 0;
        int longestSubString = 0;
        while(end<s.length()){
            int currentFreq = charsMap.getOrDefault(s.charAt(end), 0);
            if(currentFreq == 0){
                distinctElem++;
            }
            charsMap.put(s.charAt(end), ++currentFreq);
            end++;

            while(distinctElem >k){
                int stFreq = charsMap.get(s.charAt(st));
                if(stFreq == 1){
                    distinctElem--;
                }
                charsMap.put(s.charAt(st), --stFreq);
                st++;
            }
            longestSubString = Math.max(longestSubString, end-st);
        }
        return Math.max(longestSubString, end-st);
    }
}
