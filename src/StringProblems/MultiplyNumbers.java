package StringProblems;

/*
https://leetcode.com/problems/multiply-strings/
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
/*
 `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`
Running time is O(n*m)
Space needed is O(n+m)
 */
public class MultiplyNumbers {
    public String multiply(String num1, String num2) {
        int[] result = new int[num1.length()+num2.length()];
        for(int i = 0; i<num1.length(); i++){
            for(int j = 0; j<num2.length(); j++){
                int mul = (num1.charAt(i) - '0')*(num2.charAt(j) - '0');
                result[i+j+1] += mul%10;
                result[i+j] += mul/10;

            }
        }
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int stIndex = getStIndex(result);
        for(int i = result.length-1; i>=((carry>0)?stIndex-1:stIndex); i--){
            int sum = result[i] + carry;
            sb.append(sum%10);
            carry = sum/10;
        }
        return sb.reverse().toString();
    }

    int getStIndex(int[] result){
        for(int i = 0; i<result.length; i++){
            if(result[i] != 0){
                return i;
            }
        }
        return result.length-1;
    }
}
