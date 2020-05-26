package Graph;
/*
https://leetcode.com/problems/max-area-of-island/
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:

[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.
 */
/*
Running time is O(n*m)
Space needed is O(n*m)
 */
public class MaxIslandArea {
    int[][] directions = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        for(int r = 0; r<grid.length; r++){
            for(int c = 0; c<grid[0].length; c++){
                if(grid[r][c] == 1){
                    currArea = 0;
                    dfs(grid, r, c);
                    maxArea = Math.max(maxArea, currArea);
                }
            }
        }
        return maxArea;
    }
    int maxArea = 0;
    int currArea = 0;

    void dfs(int[][] grid, int r, int c){
        if(r <0 || r>= grid.length || c<0 || c>=grid[0].length || grid[r][c] != 1){
            return;
        }
        currArea++;
        grid[r][c] = 0;
        for(int[] direction: directions){
            dfs(grid, r+direction[0], c+direction[1]);
        }
    }
}
