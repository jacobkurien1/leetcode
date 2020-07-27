package matrix;
/*
https://leetcode.com/problems/island-perimeter/
You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally).
The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes" (water inside that isn't connected to the water around the island).
One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.



Example:

Input:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Output: 16

Explanation: The perimeter is the 16 yellow stripes in the image below:


 */
/*
+--+     +--+                   +--+--+
|  |  +  |  |          ->       |     |
+--+     +--+                   +--+--+
Running time O(n*m)
Space is O(1)
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int islands = 0;
        int neighbours = 0;
        for(int r = 0; r<grid.length; r++){
            for(int c = 0; c<grid[0].length; c++){
                if(grid[r][c] == 1){
                    islands++;
                    if(r+1<grid.length && grid[r+1][c] == 1) neighbours++; //bottom neighbour
                    if(c+1<grid[0].length && grid[r][c+1]==1) neighbours++; // left neighbour
                }
            }
        }
        return islands *4 - neighbours *2;
    }
}
