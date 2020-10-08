package main.java.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In one step, you can move up, down, left or right from and to an empty cell.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1)
given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.



Example 1:

Input:
grid =
[[0,0,0],
 [1,1,0],
 [0,0,0],
 [0,1,1],
 [0,0,0]],
k = 1
Output: 6
Explanation:
The shortest path without eliminating any obstacle is 10.
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).


Example 2:

Input:
grid =
[[0,1,1],
 [1,1,1],
 [1,0,0]],
k = 1
Output: -1
Explanation:
We need to eliminate at least two obstacles to find such a walk.


Constraints:

grid.length == m
grid[0].length == n
1 <= m, n <= 40
1 <= k <= m*n
grid[i][j] == 0 or 1
grid[0][0] == grid[m-1][n-1] == 0
 */
/*
Here we will do bfs with the visited as a tuple (row, col, obstaclesRemoved)
Since one cell in the grid can go in the matrix atmost k times, we arrive at the following running times.

Running time is O(n*m*k)
where grid size is n*m
and number of obstacles allowed for removal is k

Space needed is also O(n*m*k)
 */
public class ShortestPathInGridWithObstacleElimination {
    int[][] directions = new int[][] {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
    };
    public int shortestPath(int[][] grid, int k) {
        if(grid.length == 0 || grid[0].length == 0 || (grid.length== 1 && grid[0].length == 1)){
            return 0;
        }
        HashSet<String> visited = new HashSet<>();
        Queue<Location> bfsQ = new LinkedList<>();
        Location st = new Location(0,0,0);
        bfsQ.add(st);
        visited.add(st.toString());

        int step = 1;

        while(!bfsQ.isEmpty()){
            int qSize = bfsQ.size();
            for(int i = 0; i<qSize; i++){
                Location curr = bfsQ.poll();
                for(int[] direction: directions){
                    int newR = direction[0]+curr.row;
                    int newC = direction[1]+curr.col;
                    if(newR < 0 || newR>=grid.length || newC <0 || newC>=grid[0].length){
                        continue;  // went out of grid
                    }
                    int newObs = curr.obstacle;
                    if(grid[newR][newC] == 1){
                        if(curr.obstacle==k){
                            continue; // out of obstacles to remove
                        }
                        newObs++;
                    }
                    Location newLoc = new Location(newR, newC, newObs);
                    if(visited.contains(newLoc.toString())){
                        continue; // have visited this before
                    }

                    if(newR == grid.length-1 && newC == grid[0].length-1){
                        //reached end
                        return step;
                    }

                    visited.add(newLoc.toString());
                    bfsQ.add(newLoc);
                }
            }
            ++step;
        }
        return -1;
    }

    class Location{
        int row;
        int col;
        int obstacle;

        public Location(int row, int col, int obstacle){
            this.row = row;
            this.col = col;
            this.obstacle = obstacle;
        }

        public String toString(){
            return row + "#" + col + "#"+ obstacle;
        }
    }
}
