package DynamicProgramming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
https://leetcode.com/problems/word-break
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 */
/*
Running time is O(s.length^2 + wordDict.size())
 */
public class CanWordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s.length() == 0 || wordDict == null || wordDict.isEmpty()){
            return false;
        }
        Set<String> dict = new HashSet<String>();
        for(String word : wordDict){
            dict.add(word);
        }

        boolean[] isSentence = new boolean[s.length()];
        for(int i = 0; i< s.length(); i++){
            for(int j = i; j>=0; j--){
                if(dict.contains(s.substring(j, i+1)) && (j == 0 || isSentence[j-1])){
                    isSentence[i] = true;
                    break;
                }
            }
        }
        return isSentence[s.length()-1];
    }
}
