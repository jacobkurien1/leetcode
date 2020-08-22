package StringProblems;

/*
https://leetcode.com/problems/longest-palindromic-substring/
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
 */
/*
Go to each index and check for the palindrome of odd length and even length from that index.
Running time O(n^2)
Space O(1)
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        String maxPalindrome = "";
        for(int i =0; i<s.length(); i++){
            String oddPalindrome = palindromeAt(s, i, true);
            maxPalindrome = (maxPalindrome.length()<oddPalindrome.length()?oddPalindrome:maxPalindrome);
            if(i < s.length() -1){ // we could go off by 1 for even
                String evenPalindrome = palindromeAt(s, i, false);
                maxPalindrome = (maxPalindrome.length()<evenPalindrome.length()?evenPalindrome:maxPalindrome);
            }
        }
        return maxPalindrome;
    }

    String palindromeAt(String s, int index, boolean isOdd){

        int left = (isOdd)?index -1: index;
        int right = index +1;
        while(left>=0 && right<s.length()){
            if(s.charAt(left) != s.charAt(right)){
                break;
            }
            left--; right++;
        }
        return s.substring(left+1, right);
    }
}
