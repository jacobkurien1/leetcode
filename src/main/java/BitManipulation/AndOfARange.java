package BitManipulation;

/*
https://leetcode.com/problems/bitwise-and-of-numbers-range/
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4
Example 2:

Input: [0,1]
Output: 0
 */
/*
Brian Kernighan's algorithm: When we do n&(n-1) we would flip the rightmost 1 of n to 0.
Running time is O(1) as we are bounded by the number of 1's in n.
Space is O(1)
 */
public class AndOfARange {
    public int rangeBitwiseAnd(int m, int n) {
        while(m<n){
            n = n&(n-1);
        }
        return n;
    }
}
