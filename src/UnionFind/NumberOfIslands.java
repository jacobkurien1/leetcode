package UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/number-of-islands-ii/
A 2d grid map of m rows and n columns is initially filled with water.
We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate,
count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
/*
Time complexity : O(m \times n + L)O(m×n+L) where LL is the number of operations,
mm is the number of rows and nn is the number of columns.
it takes O(m \times n)O(m×n) to initialize UnionFind, and O(L)O(L) to process positions.
Note that Union operation takes essentially constant time[1] when UnionFind is implemented with both path compression and union by rank.

Space complexity : O(m \times n)O(m×n) as required by UnionFind data structure.
 */
public class NumberOfIslands {
    int[][] directions = new int[][]{{0,-1}, {-1,0},{1,0},{0,1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] parent = new int[m*n];
        int[] rank = new int[m*n];
        Arrays.fill(parent, -1);
        Arrays.fill(rank, 0);

        List<Integer> islands = new ArrayList<>();
        int islandCount = 0;
        for(int[] land:positions){
            int r = land[0];
            int c = land[1];
            if(parent[r*n+c] != -1){
                islands.add(islandCount);
                continue; // duplicate land
            }
            parent[r*n+c] = r*n+c; //make the land its own parent
            islandCount++;
            for(int[] direction:directions){
                int newR = r+direction[0];
                int newC = c+direction[1];
                if(newR<0 || newR >=m ||newC<0 ||newC>=n || parent[newR*n+newC]==-1){
                    continue;
                }
                int root = findWithPathCompression(parent, r*n+c);
                int newRoot = findWithPathCompression(parent, newR*n+newC);
                if(root != newRoot){
                    islandCount--;
                    if(rank[root] > rank[newRoot]){
                        parent[newRoot] = root;
                    }else if(rank[root] < rank[newRoot]){
                        parent[root] = newRoot;
                    } else {
                        parent[root] = newRoot;
                        rank[newRoot] +=1;
                    }
                }
            }
            islands.add(islandCount);
        }
        return islands;
    }

    int findWithPathCompression(int[] parent, int index){
        if(parent[index] != index){
            parent[index] = findWithPathCompression(parent, parent[index]);
        }
        return parent[index];
    }
}
