package DynamicProgramming;

import java.util.*;

/*
https://leetcode.com/problems/word-break-ii
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
 */
public class WordBreak {
    /*
    ------------------------------------------------------------------------------------------------------
    Algo1: Do similar to CanWordBreak and then do DFS with memoization to construct the list of sentences
    Running time is O(n^3)
    Space is O(n^3)
    ------------------------------------------------------------------------------------------------------
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ret = new ArrayList<String>();
        if(s.length() == 0 || wordDict == null || wordDict.isEmpty()){
            return ret;
        }
        Set<String> dict = new HashSet<String>();
        for(String word : wordDict){
            dict.add(word);
        }

        List<List<Integer>> nextWords = new ArrayList<List<Integer>>();
        for(int i = 0; i< s.length(); i++){
            nextWords.add(new ArrayList<Integer>());
            for(int j = i; j>=0; j--){
                if(dict.contains(s.substring(j, i+1)) && (j == 0 || !nextWords.get(j-1).isEmpty())){
                    nextWords.get(i).add(j);
                }
            }
        }

        if(!nextWords.get(s.length()-1).isEmpty()){
            ret = DFS(s, s.length()-1, nextWords, new HashMap<Integer, List<String>>());
        }
        return ret;
    }

    List<String> DFS(String inputStr, int strIndex, List<List<Integer>> nextWords, Map<Integer, List<String>> cache){
        if(strIndex < 0){
            return Collections.singletonList("");
        } else {
            if(cache.containsKey(strIndex)){
                return cache.get(strIndex);
            }
            List<String> currentSentences = new ArrayList<String>();
            for(Integer i : nextWords.get(strIndex)){
                List<String> partialSentences = DFS(inputStr, i-1 ,nextWords, cache);
                for(String s : partialSentences){
                    currentSentences.add(s + (s == ""?"": " ") + inputStr.substring(i, strIndex+1));
                }
            }
            cache.put(strIndex, currentSentences);
            return currentSentences;
        }
    }
    /*
    ------------------------------------------------------------------------------------------------------
    End Algo1
    ------------------------------------------------------------------------------------------------------
     */

    /*
    ------------------------------------------------------------------------------------------------------
    Algo2: Optimization to the Algo1 is to use DP, instead of saving the indexes, just save all the partial sentences.
    Running time is O(n^3)
    Space is O(n^3)
    ------------------------------------------------------------------------------------------------------
     */
    public List<String> wordBreakAlgo2(String s, List<String> wordDict) {
        if(s.length() == 0 || wordDict == null || wordDict.isEmpty()){
            return new ArrayList<String>();
        }
        Set<String> dict = new HashSet<String>();
        for(String word : wordDict){
            dict.add(word);
        }

        List<List<String>> sentences = new ArrayList<List<String>>();
        for(int i = 0; i< s.length(); i++){
            sentences.add(new ArrayList<String>());
            for(int j = i; j>=0; j--){
                String currentStr = s.substring(j, i+1);
                if(dict.contains(currentStr) && (j == 0 || !sentences.get(j-1).isEmpty())){
                    if(j==0){
                        sentences.get(i).add(currentStr);
                    } else{
                        for(String partialSentence : sentences.get(j-1)){
                            sentences.get(i).add(partialSentence + " " + currentStr);
                        }
                    }
                }
            }
        }
        return sentences.get(s.length()-1);
    }
    /*
    ------------------------------------------------------------------------------------------------------
    End Algo1
    ------------------------------------------------------------------------------------------------------
     */
}
