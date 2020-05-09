package Search;

/*
https://leetcode.com/problems/valid-perfect-square/
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Output: true
Example 2:

Input: 14
Output: false
 */
/*
Running time is log(n)
Space needed is O(1)
 */
public class IsPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int st = 1;
        int end = (int)Math.ceil(num/2.0); // to handle case when num = 1
        while(st<=end){
            int mid = st + (end-st)/2;
            long sq = (long)mid*mid; // need cast here else the product gets calculated in int and gets stored in long
            if(sq == num){
                return true;
            } else if(sq > num){
                end = mid-1;
            } else {
                st = mid+1;
            }
        }
        return false;
    }
}
