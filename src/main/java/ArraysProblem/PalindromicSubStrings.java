package ArraysProblem;
/*
https://leetcode.com/problems/palindromic-substrings/
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".


Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Note:

The input string length won't exceed 1000.
 */
public class PalindromicSubStrings {
    /*
    Optimal Solution: Take each center and count the palindromes
    Running time is O(n^2)
    Space is O(1)
     */
    public int countSubstrings(String s) {
        int palindromeCount = 0;
        for(int center = 0; center<s.length(); center++){
            palindromeCount += getPalindromeCount(true, center,s);
            palindromeCount += getPalindromeCount(false, center,s);
        }
        return palindromeCount;
    }

    int getPalindromeCount(boolean isOdd, int center, String s){
        int palindromeCount = 0;
        int st = center;
        int end = (isOdd)?center:center+1;
        while(st>=0 && end <s.length()){
            if(s.charAt(st--) == s.charAt(end++)){
                palindromeCount++;
            } else {
                break;
            }
        }
        return palindromeCount;
    }

    /*
    We can also solve this by DP
    Running time is O(n^2)
    Space is O(n^2)
     */
    public int countSubstrings1(String s) {
        int palindromeCount = 0;
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        for(int len = 0;len<s.length();len++){
            for(int st = 0; st<s.length(); st++){
                int end = st+len;
                if(end>=s.length()){
                    break;
                }
                if(s.charAt(st) == s.charAt(end) && ((st+1>end-1) || isPalindrome[st+1][end-1])){
                    isPalindrome[st][end] = true;
                    palindromeCount++;
                }
            }
        }
        return palindromeCount;
    }
}
