package Graph;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/number-of-distinct-islands/
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if
one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50.
 */
/*
To find distinct island, you can store the shape signature in a HashSet.
Running time is same as DFS. O(V+E) = O(n*m) where n is number of rows and m is the number of cols
Space requirement is O(n*m)
 */
public class DistinctIslands {
    private static final int[][] directions = new int[][] {{-1,0}, {1,0}, {0,-1}, {0, 1}};
    Set<String> shapes = new HashSet<>();
    boolean[][] visited;
    StringBuilder currShape;
    public int numDistinctIslands(int[][] grid) {
        if(grid.length == 0){
            return 0;
        }
        visited = new boolean[grid.length][grid[0].length];
        for(int row = 0; row<grid.length; row++){
            for(int col = 0; col<grid[0].length; col++){
                if(grid[row][col] == 1 && !visited[row][col]){
                    currShape = new StringBuilder();
                    DFS(grid, row, col);
                    String shapeSignature = currShape.toString();
                    shapes.add(shapeSignature);
                }
            }
        }
        return shapes.size();
    }

    void DFS(int[][] grid, int row, int col){
        visited[row][col] = true;

        for(int i = 0; i<directions.length; i++){
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if(newRow >=0 && newRow<grid.length &&
                    newCol >=0 && newCol < grid[0].length &&
                    grid[newRow][newCol]==1 && !visited[newRow][newCol]){
                currShape.append(Integer.toString(i)); // Saving the direction to create a shape signature
                DFS(grid, newRow, newCol);
            }
        }
        // Saving the information of going back
        // This is needed else [[1,1,0],[0,1,1],[0,0,0],[1,1,1],[0,1,0]] will yield same direction results
        currShape.append('b');
    }
}
