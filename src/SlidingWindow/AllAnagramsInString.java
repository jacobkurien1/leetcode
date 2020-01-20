package SlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
https://leetcode.com/problems/find-all-anagrams-in-a-string/
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
/*
Running time is O(n+m)
Space is O(n+m) to store all the values in the hashmap
 */
public class AllAnagramsInString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> anagrams = new ArrayList<Integer>();
        if(s.length() == 0 || p.length()== 0 || s.length()< p.length()){
            return anagrams;
        }
        HashMap<Character, Integer> hm = new HashMap<>();
        for(int i = 0; i<p.length(); i++){
            hm.put(p.charAt(i), hm.getOrDefault(p.charAt(i), 0)+1);
        }

        int diff = p.length();
        int st = 0; int end = 0;
        while(end<p.length()){
            hm.put(s.charAt(end), hm.getOrDefault(s.charAt(end), 0)-1);
            if(hm.get(s.charAt(end))>=0){
                diff--;
            } else {
                diff++;
            }
            end++;
        }
        if(diff == 0){
            anagrams.add(st);
        }
        while(end<s.length()){
            char toRemove = s.charAt(st);
            char toAdd = s.charAt(end);
            hm.put(toRemove, hm.get(toRemove)+1);
            if(hm.get(toRemove) <= 0){
                diff--;
            } else {
                diff++;
            }

            hm.put(toAdd, hm.getOrDefault(toAdd, 0)-1);
            if(hm.get(toAdd) >=0){
                diff--;
            } else {
                diff++;
            }
            st++;
            end++;
            if(diff == 0){
                anagrams.add(st);
            }
        }
        return anagrams;
    }
}
