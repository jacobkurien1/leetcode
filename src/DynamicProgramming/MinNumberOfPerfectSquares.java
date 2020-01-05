package DynamicProgramming;
/*
https://leetcode.com/problems/perfect-squares/
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 */
/*
Running time O(n sqrt(n))
Space is O(n)
 */
public class MinNumberOfPerfectSquares {
    public int numSquares(int n) {
        int[] minSquares = new int[n+1];

        for(int i = 1; i<=n; i++){
            if(Math.pow((int)Math.sqrt(i), 2) == i){
                minSquares[i] = 1;
            } else {
                minSquares[i] = Integer.MAX_VALUE;
                for(int j = 1; j<=Math.sqrt(i); j++ ){
                    minSquares[i] = Math.min(minSquares[i], minSquares[j*j] + minSquares[i-(j*j)]);
                }
            }
        }
        return minSquares[n];
    }
}
