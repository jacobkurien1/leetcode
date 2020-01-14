package StringProblems;

/*
https://leetcode.com/problems/valid-palindrome/
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
 */
/*
Running time is O(n)
Space is O(1)
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length()-1;
        while(l<r){
            while(l<r && !isAlphanumeric(s.charAt(l))){
                l++;
            }
            while(l<r && !isAlphanumeric(s.charAt(r))){
                r--;
            }
            if(l<r && !(Character.toLowerCase(s.charAt(l))==Character.toLowerCase(s.charAt(r)))){
                return false;
            } else{
                l++;
                r--;
            }
        }
        return true;
    }

    boolean isAlphanumeric(char c){
        return (c-'A'>=0 && c-'A'<26) || (c-'a'>=0 && c-'a'<26) || (c-'0'>=0 && c-'0'<=9);
    }
}
