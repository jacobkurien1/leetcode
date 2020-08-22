package Graph;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/shortest-path-in-binary-matrix/
In an N by N square grid, each cell is either empty (0) or blocked (1).

A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.



Example 1:

Input: [[0,1],[1,0]]


Output: 2

Example 2:

Input: [[0,0,0],[1,1,0],[1,1,0]]


Output: 4



Note:

1 <= grid.length == grid[0].length <= 100
grid[r][c] is 0 or 1
 */
/*
Running time is O(N*M)
Space needed is O(1)

This algorithm can be sped up using A* algorithm
 */
public class ShortestPathInBinaryMatrix {
    int[][] directions = new int[][]{
            {-1, 0},
            {1, 0},
            {-1, 1},
            {-1, -1},
            {1, 1},
            {1, -1},
            {0, 1},
            {0, -1}
    };
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid.length ==0){
            return 0;
        }
        if(grid[0][0] == 1 || grid[grid.length-1][grid[0].length-1] == 1){
            return -1;
        }
        if(grid.length ==1 && grid[0].length ==1){
            return 1;
        }
        List<int[]> level = new ArrayList<>();
        int[] st = new int[]{0, 0};
        level.add(st);
        int distance = 1;
        grid[0][0] = 1;
        while(!level.isEmpty()){
            List<int[]> nextLevel = new ArrayList<>();
            for(int[] curr: level){
                for(int[] direction: directions){
                    int nextR = curr[0] + direction[0];
                    int nextC = curr[1] + direction[1];
                    if(nextR<0 || nextR>=grid.length || nextC <0 || nextC >=grid[0].length
                            || grid[nextR][nextC] == 1){
                        continue;
                    }
                    if(nextR == grid.length-1 && nextC == grid[0].length-1){
                        // reached the final destination
                        return distance +1;
                    }
                    int[] nextPt = new int []{nextR, nextC};
                    nextLevel.add(nextPt);
                    grid[nextR][nextC] = 1;
                }
            }
            level = nextLevel;
            distance++;
        }
        return -1;
    }
}
