package StringProblems;

/*
https://leetcode.com/problems/add-binary/
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"


Constraints:

Each string consists only of '0' or '1' characters.
1 <= a.length, b.length <= 10^4
Each string is either "0" or doesn't contain any leading zero.
 */
/*
Running time is O(max(a.length, b.length())
Space needed is O(max(a.length, b.length()) to store output
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        int aIndex = a.length()-1;
        int bIndex = b.length()-1;
        int carry =0;
        StringBuilder sb = new StringBuilder();
        while(aIndex>=0 || bIndex>=0){
            int aVal = (aIndex>=0)?Integer.parseInt(Character.toString(a.charAt(aIndex--))):0;
            int bVal = (bIndex>=0)?Integer.parseInt(Character.toString(b.charAt(bIndex--))):0;
            int sum = aVal + bVal + carry;
            sb.append(Integer.toString(sum%2));
            carry = sum/2;
        }
        if(carry >0){
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
