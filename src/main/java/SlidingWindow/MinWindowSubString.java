package SlidingWindow;

import java.util.HashMap;

/*
https://leetcode.com/problems/minimum-window-substring/
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
/*
Running time is o(t.length + s.length)
Space needed is O(t.length)
 */
public class MinWindowSubString {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for(char c: t.toCharArray()){
            int freq = freqMap.getOrDefault(c, 0);
            freqMap.put(c, ++freq);
        }
        int matchesNeeded = freqMap.size();

        int st = 0;
        int end = 0;
        int minSt = 0;
        int minEnd = s.length();
        while(end<s.length()){
            if(freqMap.containsKey(s.charAt(end))){
                freqMap.put(s.charAt(end), freqMap.get(s.charAt(end))-1);
                if(freqMap.get(s.charAt(end))==0){
                    matchesNeeded--;

                }
            }
            while(matchesNeeded == 0 && st<=end){
                if(matchesNeeded == 0 && minEnd-minSt> end-st){
                    minEnd = end;
                    minSt = st;
                }

                if(freqMap.containsKey(s.charAt(st))){
                    freqMap.put(s.charAt(st), freqMap.get(s.charAt(st))+1);
                    if(freqMap.get(s.charAt(st)) >0){
                        matchesNeeded++;
                    }
                }
                st++;
            }
            end++;
        }
        return minEnd==s.length()?"":s.substring(minSt, minEnd+1);
    }
}
