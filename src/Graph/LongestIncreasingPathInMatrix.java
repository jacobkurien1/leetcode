package Graph;

import java.util.List;
import java.util.Arrays;

/*
https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums =
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:

Input: nums =
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
/*
Solution: Go to each cell and do DFS on that cell and keep track of the longest path.
Trick: Since we need to find the increasing numbers, hence we can go only in one direction,
Hence we can save the longest path in the visited matrix(cache) and reuse it rather than
calculating it again and again.
 */
public class LongestIncreasingPathInMatrix {
    public static final List<Direction> directions = Arrays.asList(
            new Direction(0,-1),
            new Direction(-1,0),
            new Direction(0,1),
            new Direction(1, 0)
    );
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length ==0){
            return 0;
        }
        int longestPath = 0;
        int[][] visited = new int[matrix.length][matrix[0].length];
        for(int row =0; row<matrix.length; row++){
            for(int col = 0; col<matrix[0].length; col++){
                longestPath = Math.max(longestPath, DFS(matrix, row, col, visited));
            }
        }
        return longestPath;
    }

    int DFS(int[][] matrix, int row, int col, int[][] visited){
        if(visited[row][col]!=0){
            return visited[row][col];
        }
        int longestPath = 1;
        for(Direction direction : directions){
            int newRow = row+direction.x;
            int newCol = col+direction.y;
            if(newRow >=0 && newRow<matrix.length && newCol>=0 && newCol<matrix[0].length
                    && matrix[row][col] < matrix[newRow][newCol]){
                longestPath = Math.max(1 + DFS(matrix, newRow, newCol, visited), longestPath);
            }
        }
        visited[row][col] = longestPath;
        return longestPath;
    }

    static class Direction{
        int x;
        int y;
        Direction(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
