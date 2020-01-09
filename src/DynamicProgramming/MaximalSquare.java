package DynamicProgramming;
/*
https://leetcode.com/problems/maximal-square/
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
 */
/*
Running time O(n*m)
Space O(m)
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int maxSq = 0;
        if(matrix.length == 0){
            return maxSq;
        }
        int[] prev = new int[matrix[0].length];
        int[] curr = new int[matrix[0].length];
        for(int row = 0; row<matrix.length; row++){
            for(int col = 0; col<matrix[0].length; col++){
                if(matrix[row][col]=='1'){
                    int minVal = Integer.MAX_VALUE;
                    minVal = Math.min(minVal, prev[col]);
                    minVal = Math.min(minVal, col-1<0?0:curr[col-1]);
                    minVal = Math.min(minVal, col-1<0?0:prev[col-1]);

                    curr[col] =  minVal+1;
                    maxSq = Math.max(curr[col], maxSq);
                }
            }
            prev = curr;
            curr = new int[matrix[0].length];
        }
        return maxSq*maxSq;
    }
}
