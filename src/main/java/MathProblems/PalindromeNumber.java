package MathProblems;

/*
https://leetcode.com/problems/palindrome-number/
Given an integer x, return true if x is palindrome integer.

An integer is a palindrome when it reads the same backward as forward. For example, 121 is palindrome while 123 is not.



Example 1:

Input: x = 121
Output: true

Example 2:

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Example 4:

Input: x = -101
Output: false



Constraints:

    -231 <= x <= 231 - 1


Follow up: Could you solve it without converting the integer to a string?
 */
/*
Running time is O(log10(n))
Space is O(1)
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int reverted = 0;
        int orig = x;
        while(x>0){
            reverted *= 10;
            reverted += x%10;
            x /=10;
        }
        return orig == reverted;
    }
}
