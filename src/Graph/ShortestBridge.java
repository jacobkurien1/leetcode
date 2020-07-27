package Graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/shortest-bridge/
In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)

Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)



Example 1:

Input: A = [[0,1],[1,0]]
Output: 1
Example 2:

Input: A = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1


Constraints:

2 <= A.length == A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1
 */
/*
Running time is O(m*n)
Space needed is O(m*n)
 */
public class ShortestBridge {
    public int shortestBridge(int[][] A) {
        HashSet<String> firstEdge = new HashSet<>();
        HashSet<String> firstVisited = new HashSet<>();

        HashSet<String> secondEdge = new HashSet<>();
        HashSet<String> secondVisited = new HashSet<>();
        for(int i = 0; i<A.length; i++){
            for(int j = 0; j<A[0].length; j++){
                if(A[i][j] == 1){
                    String cell = Arrays.toString(new String[]{Integer.toString(i), Integer.toString(j)});
                    if(firstVisited.isEmpty()){
                        getBoundaryTouchingWater(A, i, j, firstEdge, firstVisited);
                    } else if(!firstVisited.contains(cell) && !secondVisited.contains(cell)){
                        getBoundaryTouchingWater(A, i, j, secondEdge, secondVisited);
                    }
                }

            }
        }

        Queue<String> q = new LinkedList<String>();
        HashSet<String> visitedWater = new HashSet<>();
        for(String edgeNode: firstEdge){
            q.add(edgeNode);
        }
        int bridgeLen = 0;
        while(!q.isEmpty()){
            int qSize = q.size();
            for(int i = 0; i<qSize; i++){
                String cell = q.poll();
                String[] cellParts = cell.substring(1, cell.length()-1).split(", ");
                int r = Integer.parseInt(cellParts[0]);
                int c = Integer.parseInt(cellParts[1]);
                for(int[] direction:directions){
                    int newR = r+direction[0];
                    int newC = c+direction[1];
                    String newCell = Arrays.toString(new String[]{Integer.toString(newR), Integer.toString(newC)});
                    if(newR<0 || newR>=A.length || newC<0 || newC>=A[0].length || firstVisited.contains(newCell) || visitedWater.contains(newCell)){
                        continue;
                    }
                    if(A[newR][newC] == 0){
                        visitedWater.add(newCell);
                        q.add(newCell);
                    }else if (secondVisited.contains(newCell)){
                        return bridgeLen;
                    }
                }
            }
            bridgeLen++;
        }
        return -1;
    }
    int[][] directions = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    void getBoundaryTouchingWater(int[][] a, int r, int c, HashSet<String> islandEdge, HashSet<String> visited){
        String cell = Arrays.toString(new String[]{Integer.toString(r), Integer.toString(c)});
        visited.add(cell);
        boolean isEdge = false;
        for(int[] direction:directions){
            int newR = r+direction[0];
            int newC = c+direction[1];
            String newCell = Arrays.toString(new String[]{Integer.toString(newR), Integer.toString(newC)});
            if(newR<0 || newR>=a.length || newC<0 || newC>=a[0].length || visited.contains(newCell)){
                continue;
            }
            if(a[newR][newC] == 0){
                isEdge = true;
            }else{
                getBoundaryTouchingWater(a, newR, newC, islandEdge, visited);
            }
        }
        if(isEdge){
            islandEdge.add(cell);
        }
    }
}
