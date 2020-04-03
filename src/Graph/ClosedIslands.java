package Graph;

/*
https://leetcode.com/problems/number-of-closed-islands/
Given a 2D grid consists of 0s (land) and 1s (water).
An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.



Example 1:



Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation:
Islands in gray are closed because they are completely surrounded by water (group of 1s).
Example 2:



Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:

Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2


Constraints:

1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1
 */
/*
Running time is O(n*m)
Space needed is O(n*m) for worst case where we have to visit all cells and they are in the call stack
 */
public class ClosedIslands {
    public int closedIsland(int[][] grid) {
        if(grid.length == 0){
            return 0;
        }
        int islandCount = 0;
        for(int i =0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length; j++){
                if(grid[i][j] == 0 &&  dfs(grid, i, j)){
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    boolean dfs(int[][] grid, int r, int c){
        if(r>=grid.length || r<0 || c>=grid[0].length || c<0){
            return false;
        }
        if(grid[r][c] == 1){
            return true;
        }
        grid[r][c] = 1;
        boolean isIsland = true;
        for(Direction direction : directions){
            isIsland &= dfs(grid, r+direction.x, c+direction.y);
        }
        return isIsland;
    }

    private Direction[] directions = new Direction[]{
            new Direction(0,-1),
            new Direction(0,1),
            new Direction(1,0),
            new Direction(-1,0)
    };

    class Direction{
        int x;
        int y;
        Direction(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
