package StringProblems;

/*
https://leetcode.com/problems/valid-palindrome-ii/
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
/*
Running time is O(n)
Space needed is O(1)
 */
public class ValidPalindromeAtmost1Mismatch {
    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length()-1;
        return isPalindrome(s, l, r, true);
    }

    boolean isPalindrome(String s, int l, int r, boolean canDelete){
        while(l<r){
            if(s.charAt(l) != s.charAt(r)){
                if(!canDelete){
                    return false;
                } else {
                    return isPalindrome(s, l+1, r, false) ||
                            isPalindrome(s, l, r-1, false);
                }
            }
            l++;
            r--;
        }
        return true;
    }
}
