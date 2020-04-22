package Graph;

/*
https://leetcode.com/problems/number-of-islands/
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */
/*
Running time is O(n*m)
Space needed is O(1)
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int islands = 0;
        if(grid.length == 0){
            return islands;
        }
        for(int r = 0; r<grid.length; r++){
            for(int c = 0; c<grid[0].length; c++){
                if(grid[r][c] == '1'){
                    dfs(grid, r,c);
                    islands++;
                }
            }
        }
        return islands;
    }

    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0,1}, {0, -1}};
    void dfs(char[][] grid , int r, int c){
        if(r<0 || r>=grid.length || c<0 || c>=grid[0].length || grid[r][c] == '0'){
            return;
        }
        grid[r][c] = '0';
        for(int[] direction: directions){
            dfs(grid, r+direction[0], c+direction[1]);
        }
    }
}
