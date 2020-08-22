package Misc;

/*
https://leetcode.com/problems/powx-n/
Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
Note:

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */
/*
Running time is O(logn)
Space needed is O(logn)
 */
public class PowerOfNumber {
    public double myPow(double x, int n) {
        if(n==0 || x == 1){
            return 1.0;
        }
        long power = (long)n;
        if(power<0) {
            x = 1/x;
            power *= -1;
        }

        return positivePower(x, power);
    }

    double positivePower(double x, long n){
        if(n == 1){
            return x;
        }
        double intermediate = positivePower(x, n/2);
        if(n % 2 != 0){
            return intermediate * intermediate * x;
        } else {
            return intermediate * intermediate;
        }
    }
}
