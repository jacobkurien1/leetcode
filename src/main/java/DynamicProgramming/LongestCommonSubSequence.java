package DynamicProgramming;

/*
https://leetcode.com/problems/longest-common-subsequence/
Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted
without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not).
A common subsequence of two strings is a subsequence that is common to both strings.



If there is no common subsequence, return 0.



Example 1:

Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.


Constraints:

1 <= text1.length <= 1000
1 <= text2.length <= 1000
The input strings consist of lowercase English characters only.
 */
/*
Running time is O(n*m)
Space needed is O(min(m,n))
 */
public class LongestCommonSubSequence {
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1.length() > text2.length()){
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }
        int[] prev = new int[text1.length()];
        for(int i = 0; i<text2.length();i++){
            int[] curr = new int[text1.length()];
            for(int j =0; j<text1.length();j++){
                if(text2.charAt(i) == text1.charAt(j)){
                    curr[j] = (j-1<0)?1:prev[j-1]+1;
                } else {
                    curr[j] = (j-1<0)?prev[j]:Math.max(prev[j], curr[j-1]);
                }
            }
            prev = curr;
        }
        return prev[text1.length()-1];
    }
}
