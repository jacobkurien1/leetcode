package DynamicProgramming;

import java.util.Arrays;

/*
https://leetcode.com/problems/minimum-path-sum/
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MinPathMatrix {
    /*
    Algo : use the input grid to store the results
    Running time is O(n*m)
    Space needed is O(1)
     */
    public int minPathSumOptimized(int[][] grid) {
        if(grid.length == 0){
            return 0;
        }
        for(int row = 0; row<grid.length; row++){
            for(int col = 0; col<grid[0].length; col++){
                if(row != 0 || col != 0){
                    grid[row][col] += Math.min(((row==0)?Integer.MAX_VALUE:grid[row-1][col]),
                            ((col == 0)?Integer.MAX_VALUE:grid[row][col-1]));
                }
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }

    /*
    Running time O(n*m)
    Space needed is O(m)

    We can have space as O(1) if we use the grid to store the minpathsum.
     */
    public int minPathSum(int[][] grid) {
        if(grid.length == 0){
            return 0;
        }
        int[] prev = new int[grid[0].length];
        Arrays.fill(prev, Integer.MAX_VALUE);
        prev[0] = 0;
        for (int i = 0; i<grid.length;i++){
            int[] curr = new int[grid[0].length];
            for(int j = 0;j<curr.length;j++){
                curr[j] = ((j ==0)? prev[j] : Math.min(prev[j], curr[j-1])) + grid[i][j];
            }
            prev = curr;
        }
        return prev[grid[0].length-1];
    }
}
