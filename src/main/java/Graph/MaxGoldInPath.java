package Graph;

/*
https://leetcode.com/problems/path-with-maximum-gold/
In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position you can walk one step to the left, right, up or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.


Example 1:

Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.
Example 2:

Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
Output: 28
Explanation:
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.


Constraints:

1 <= grid.length, grid[i].length <= 15
0 <= grid[i][j] <= 100
There are at most 25 cells containing gold.
 */
/*
Solution: Do DFS in each cell.
Running time will be O((k * (4^k)) + (m*n)) where k is the cells with gold, m - #ofRows, n - #ofCols
Space O(m*n)
 */
public class MaxGoldInPath {
    public int getMaximumGold(int[][] grid) {
        int maxGold = 0;
        for(int row = 0; row<grid.length; row++){
            for(int col = 0; col<grid[0].length; col++){
                if(grid[row][col] != 0){
                    maxGold = Math.max(maxGold, DFS(row, col, grid));
                }
            }
        }
        return maxGold;
    }

    int DFS(int row, int col, int[][] grid){
        if(row<0 || row >= grid.length || col <0 || col>=grid[0].length || grid[row][col] <= 0){
            return 0;
        }
        int maxGold = 0;
        grid[row][col] *= -1;
        maxGold = Math.max(maxGold, DFS(row+1, col, grid));
        maxGold = Math.max(maxGold, DFS(row-1, col, grid));
        maxGold = Math.max(maxGold, DFS(row, col+1, grid));
        maxGold = Math.max(maxGold, DFS(row, col-1, grid));
        grid[row][col] *= -1;
        return maxGold + grid[row][col];
    }
}
