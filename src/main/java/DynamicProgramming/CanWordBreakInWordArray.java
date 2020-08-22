package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
https://leetcode.com/problems/concatenated-words
Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.
 */
/*
Running time is O(L^2 *n + nlog(n)) where n is the number of words and L is the length of the words
Space needed is O(n+l) for hashset and the array canBreak;
 */
public class CanWordBreakInWordArray {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ret = new ArrayList<>();
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));

        HashSet<String> hs  = new HashSet();
        for(String word: words){
            if(formedByConcat(hs, word)){
                ret.add(word);
            }
            hs.add(word);
        }
        return ret;
    }

    boolean formedByConcat(HashSet<String> hs, String word){
        if(word.length() == 0){
            return false;
        }
        boolean[] canBreak = new boolean[word.length()];
        for(int i = 0; i<word.length(); i++){
            for(int j = i; j>=0; j--){
                if(hs.contains(word.substring(j, i+1)) && (j==0 || canBreak[j-1])){
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return canBreak[word.length()-1];
    }
}
