package Greedy;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/path-with-maximum-minimum-value/
Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].

The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.

A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).



Example 1:



Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation:
The path with the maximum score is highlighted in yellow.
Example 2:



Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2
Example 3:



Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3


Note:

1 <= R, C <= 100
0 <= A[i][j] <= 10^9
 */
/*
Variation on Dijkstra/DFS
Running time is O(n*m)
Space needed is O(n*m)
 */
public class MinInMaxPath {
    public int maximumMinimumPath(int[][] grid) {
        int minInMaxPath=Integer.MAX_VALUE;
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.val, a.val));
        pq.add(new Cell(0,0,grid[0][0]));
        grid[0][0] = -1;
        while(!pq.isEmpty()){
            Cell c = pq.poll();
            if(c.val < minInMaxPath){
                minInMaxPath = c.val;
            }
            if(c.row == grid.length-1 && c.col == grid[0].length-1){
                return minInMaxPath;
            }
            for(int[] direction : directions){
                int newR = c.row + direction[0];
                int newC = c.col + direction[1];

                if(newR<0 || newR >= grid.length || newC <0 || newC>=grid[0].length || grid[newR][newC] == -1){
                    continue;
                }

                pq.add(new Cell(newR, newC, grid[newR][newC]));
                grid[newR][newC] = -1;
            }
        }
        return -1;
    }

    class Cell{
        int row;
        int col;
        int val;
        public Cell(int row, int col, int val){
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    int[][] directions = new int[][] {{1,0},
            {-1,0},
            {0,1},
            {0,-1}};
}
