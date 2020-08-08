package Misc;

import java.util.HashSet;

/*
https://leetcode.com/problems/happy-number/
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process:
Starting with any positive integer, replace the number by the sum of the squares of its digits,
and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy numbers.

Example:

Input: 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
*/
/*
Here we are using the HashSet. We can also use the Floyd cycle detection algorithm as well.

Digits	Largest	Next
1	9	81
2	99	162
3	999	243
4	9999	324
13	9999999999999	1053

So we even if we start at a higher number we will always come back to <=243.

Running time is O(log(n))
So Running time of inner while loop to get the sq of all digits is log(n), as log(n) gives the number of digits.
O(243*3 + logn + log(logn) + log(log(logn)) + ... ) = O(logn)

Space neeeded will also be O(logn), but we can reduce it to O(243)= O(1) by storing only elements <=243
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        int prev = n;
        HashSet<Integer> hs = new HashSet<>();

        while(!hs.contains(prev)){
            hs.add(prev);
            int curr = 0;
            while(prev!=0){
                int lastDigit = prev%10;
                curr += lastDigit * lastDigit;
                prev /= 10;
            }
            prev = curr;
        }
        return prev == 1;
    }
}
