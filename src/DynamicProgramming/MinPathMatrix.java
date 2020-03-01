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
/*
Running time O(n*m)
Space needed is O(m)

We can have space as O(1) if we use the grid to store the minpathsum.
 */
public class MinPathMatrix {
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
