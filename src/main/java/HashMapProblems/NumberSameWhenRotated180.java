package HashMapProblems;

import java.util.HashMap;

/*
https://leetcode.com/problems/strobogrammatic-number/
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false
 */
/*
Running time O(n)
Space needed O(1)
 */
public class NumberSameWhenRotated180 {
    public boolean isStrobogrammatic(String num) {
        HashMap<Character, Character> hm = new HashMap<>();
        hm.put('0', '0');
        hm.put('1', '1');
        hm.put('6','9');
        hm.put('9','6');
        hm.put('8', '8');

        if(num == null || num.length() == 0){
            return false;
        }

        int l = 0;
        int r = num.length()-1;
        while(l<=r){
            char rotatedCounterPart = hm.getOrDefault(num.charAt(l), '#');
            if(rotatedCounterPart != num.charAt(r)){
                return false;
            }
            l++; r--;
        }
        return true;
    }
}
