package main.java.DynamicProgramming;

/*
https://leetcode.com/problems/valid-palindrome-iii/
Given a string s and an integer k, find out if the given string is a K-Palindrome or not.

A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.



Example 1:

Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.


Constraints:

1 <= s.length <= 1000
s has only lowercase English letters.
1 <= k <= s.length
 */
/*
Running time is O(n^2)
Space needed is O(n^2)
 */
public class ValidPalindrome {
    public boolean isValidPalindrome(String s, int k) {
        int[][] longestPalindrome = new int[s.length()][s.length()];
        for(int len = 1; len<=s.length(); len++){
            for(int i = 0; i<s.length(); i++){
                int j = i+len-1;
                if(j>=s.length()){
                    break;
                }
                if(s.charAt(i) == s.charAt(j)){
                    longestPalindrome[i][j] = (i==j?1:2)
                            +(i+1<s.length() && j-1>=0?longestPalindrome[i+1][j-1]:0);
                } else {
                    if(i+1<s.length()){
                        longestPalindrome[i][j] = Math.max(longestPalindrome[i][j], longestPalindrome[i+1][j]);
                    }
                    if(j-1>=0){
                        longestPalindrome[i][j] = Math.max(longestPalindrome[i][j], longestPalindrome[i][j-1]);
                    }
                }
            }
        }
        return s.length() - longestPalindrome[0][s.length()-1] <=k;
    }
}
