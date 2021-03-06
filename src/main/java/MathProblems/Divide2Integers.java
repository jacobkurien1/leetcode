package MathProblems;

import java.util.ArrayList;
import java.util.List;
/*
https://leetcode.com/problems/divide-two-integers/
Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = truncate(3.33333..) = 3.
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = truncate(-2.33333..) = -2.
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 */
/*
Running time O(log(n))
Space needed is O(log(n))
 */
public class Divide2Integers {
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1){
            return Integer.MAX_VALUE; // overflow case;
        }
        boolean isNegative = ((dividend>0 && divisor<0) || (dividend <0 && divisor>0));
        if(dividend >0){
            dividend = 0-dividend;
        }
        if(divisor >0){
            divisor = 0-divisor;
        }
        List<Integer> divisorArr = new ArrayList<>();
        List<Integer> multiplierArr = new ArrayList<>();
        int currDivisor = divisor;
        int currMultiplier = -1;
        int quotient = 0;
        while(dividend <= currDivisor){
            divisorArr.add(currDivisor);
            multiplierArr.add(currMultiplier);
            quotient += currMultiplier;
            dividend -= currDivisor;
            if(currDivisor < Integer.MIN_VALUE/2){
                break;
            }
            currDivisor += currDivisor;
            currMultiplier += currMultiplier;
        }
        for(int i = divisorArr.size()-1; i>=0; i--){
            if(dividend <= divisorArr.get(i)){
                dividend -= divisorArr.get(i);
                quotient += multiplierArr.get(i);
            }
        }
        return isNegative?quotient:(quotient == Integer.MIN_VALUE?Integer.MAX_VALUE:0-quotient);
    }
}
