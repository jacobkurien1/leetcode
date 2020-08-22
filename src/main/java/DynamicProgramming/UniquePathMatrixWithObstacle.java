package DynamicProgramming;

/*
https://leetcode.com/problems/unique-paths-ii/
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid.

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right

 */
public class UniquePathMatrixWithObstacle {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0][0] == 1 ||
                obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1] == 1){
            return 0;
        }

        for(int row = 0; row < obstacleGrid.length; row++){
            for(int col = 0; col < obstacleGrid[0].length; col++){
                if (obstacleGrid[row][col] == 1){
                    obstacleGrid[row][col] = 0;
                } else {
                    if(row == 0 && col ==0){
                        obstacleGrid[0][0] = 1;
                    }
                    if(row-1>=0){
                        obstacleGrid[row][col] += obstacleGrid[row-1][col];
                    }
                    if(col-1>=0){
                        obstacleGrid[row][col] += obstacleGrid[row][col-1];
                    }
                }
            }
        }
        return obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }
}
