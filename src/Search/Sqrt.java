package Search;

/*
https://leetcode.com/problems/sqrtx/
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.
 */
/*
Solution: use binary search from 0 to x/2 to find the sqrt
 */
public class Sqrt {
    public int mySqrt(int x) {
        if(x == 0 || x ==1){
            return x;
        }
        return getSqrtByBinarySearch(x, 1, x/2);
    }

    int getSqrtByBinarySearch(int x, int low, int high){
        int ret = 0;
        while(low<=high){
            int mid = high - ((high - low)/2);
            if(mid == x/mid){ //This is done to prevent overflow (cannot do i*i without checking for overflow)
                return mid;
            } else if (mid<x/mid){
                ret = mid;
                low = mid+1;
            } else {
                high = mid -1;
            }
        }
        return ret;
    }
}
