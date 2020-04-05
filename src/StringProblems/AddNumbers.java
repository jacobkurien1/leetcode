package StringProblems;

/*
https://leetcode.com/problems/add-strings
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddNumbers {
    public String addStrings(String num1, String num2) {
        int index1 = num1.length()-1;
        int index2 = num2.length()-1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while(index1>=0 || index2>=0 || carry>0){
            int n1 = (index1 >=0)? num1.charAt(index1--)-'0':0;
            int n2 = (index2 >=0)? num2.charAt(index2--)-'0':0;
            int sum = n1+n2+carry;
            sb.append(Integer.toString(sum%10));
            carry = sum/10;
        }
        return sb.reverse().toString();
    }
}
