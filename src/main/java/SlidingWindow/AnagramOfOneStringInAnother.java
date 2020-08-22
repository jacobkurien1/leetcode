package SlidingWindow;

import java.util.HashMap;

/*
https://leetcode.com/problems/permutation-in-string/
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
In other words, one of the first string's permutations is the substring of the second string.



Example 1:

Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input:s1= "ab" s2 = "eidboaoo"
Output: False


Note:

The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
 */
/*
Running time is O(n+m)
Space is O(1)
 */
public class AnagramOfOneStringInAnother {
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> freq = new HashMap<>();
        int distinctChars = 0;
        for(char c: s1.toCharArray()){
            int charFreq = freq.getOrDefault(c, 0);
            if(charFreq == 0){
                distinctChars++;
            }
            freq.put(c, charFreq+1);
        }

        int st= 0; int end = 0;
        while(end<s2.length()){
            if(freq.containsKey(s2.charAt(end))){
                int charFreq = freq.get(s2.charAt(end));
                if(charFreq == 1){
                    distinctChars--;
                    if(distinctChars == 0){
                        return true;
                    }
                }
                freq.put(s2.charAt(end), charFreq-1);
            }
            end++;
            if(end -st ==s1.length()){ // check for off by 1
                if(freq.containsKey(s2.charAt(st))){
                    int charFreq = freq.get(s2.charAt(st));
                    if(charFreq == 0){
                        distinctChars++;
                    }
                    freq.put(s2.charAt(st), charFreq+1);
                }
                st++;
            }
        }

        return false;
    }
}
