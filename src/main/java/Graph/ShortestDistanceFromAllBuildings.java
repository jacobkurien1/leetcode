package Graph;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/shortest-distance-from-all-buildings/
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
Example:

Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 7

Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
             the point (1,2) is an ideal empty land to build a house, as the total
             travel distance of 3+3+1=7 is minimal. So return 7.
Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */
/*
Running time O(#ofBuildings * O(V+E)) = O(#buildings * O(4V)) = O(#buildings * O(m*n))
loose bound Running time is O(n^2*m^2) as #buildings can be n*m

a more tighter bound = O(#buildings*#emptyspaces)

Space needed = O(n*m)
 */
public class ShortestDistanceFromAllBuildings {
    int[][] directions = new int[][]{
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };
    public int shortestDistance(int[][] grid) {

        // Need to keep count of buildings and check whether all buildings
        // are accesible at an empty space
        int buildings = 0;
        int[][] visited = new int[grid.length][grid[0].length];
        int[][] distance = new int[grid.length][grid[0].length];

        for(int r = 0; r<grid.length; r++){
            for(int c = 0; c<grid[0].length; c++){
                if(grid[r][c] == 1){
                    buildings++;
                    bfs(grid, r,c, visited, distance);
                }
            }
        }

        int minDist = Integer.MAX_VALUE;
        for(int r = 0; r<grid.length; r++){
            for(int c = 0; c<grid[0].length; c++){
                if(grid[r][c]==0 && visited[r][c] == buildings){
                    minDist = Math.min(minDist, distance[r][c]);
                }
            }
        }

        return ((minDist == Integer.MAX_VALUE)?-1:minDist);
    }

    void bfs(int[][] grid, int r, int c, int[][] visitedByBuilding, int[][] distGrid){
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        int distance = 0;
        while(!q.isEmpty()){
            int qSize =q.size();
            for(int i = 0;i<qSize; i++){
                int[] cell = q.poll();
                if(distance != 0){
                    distGrid[cell[0]][cell[1]] += distance;
                }
                for(int[] direction:directions){
                    int newR = cell[0]+direction[0];
                    int newC = cell[1]+direction[1];
                    if(newR>=0 && newR<grid.length && newC >=0 && newC<grid[0].length && grid[newR][newC]==0 && !visited[newR][newC]){
                        visited[newR][newC] = true;
                        visitedByBuilding[newR][newC] += 1;
                        q.add(new int[]{newR, newC});
                    }
                }
            }
            distance++;
        }
    }
}
