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
        List<Integer> ret = new ArrayList<>();
        if(s == null || p == null|| s.length() == 0|| p.length() == 0){
            return ret;
        }
        HashMap<Character, Integer> hm = new HashMap<>();
        for(int i =0; i<p.length();i++){
            int freq = hm.getOrDefault(p.charAt(i), 0);
            hm.put(p.charAt(i), freq+1);
        }
        int distinctChars = hm.size();

        int st = 0; int end = 0;
        while(end<s.length()){
            if(end-st >=p.length()){
                if(hm.containsKey(s.charAt(st))){
                    int freq  = hm.get(s.charAt(st));
                    if(freq == 0){
                        distinctChars++;
                    }
                    hm.put(s.charAt(st), freq+1);
                }
                st++;
            }
            if(hm.containsKey(s.charAt(end))){
                int freq = hm.get(s.charAt(end));
                if(freq -1 == 0){
                    distinctChars--;
                    if(distinctChars == 0){
                        ret.add(st);
                    }
                }
                hm.put(s.charAt(end), freq-1);
            }
            end++;
        }
        return ret;
    }
}
