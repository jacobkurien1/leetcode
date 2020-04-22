package Graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/minimum-knight-moves/
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below.
Each move is two squares in a cardinal direction, then one square in an orthogonal direction.



Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.



Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]


Constraints:

|x| + |y| <= 300
 */
/*
Running time is O(4^(distance of pt from origin)
Space needed is O(n*m) to save all the visited nodes
 */
public class MinimumKnightMoves {
    int[][] directions = new int[][]{
            {1,2},
            {2,1},
            {-1,2},
            {2,-1}
    };

    int[][] allDirections = new int[][]{
            {1,2},
            {2,1},
            {1,-2},
            {2,-1},
            {-1,2},
            {-2,1},
            {-1,-2},
            {-2,-1}
    };
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        HashSet<String> visited = new HashSet<>();
        visited.add(Arrays.toString(q.peek()));
        int moves =0;
        while(!q.isEmpty()){
            int qSize = q.size();
            for(int i = 0; i<qSize; i++){
                int[] cell = q.poll();
                if(cell[0] == x && cell[1] == y){
                    return moves;
                }
                int[][] directionTaken = (getDistance(cell, new int[]{x,y})>6)?directions:allDirections;
                for(int[] direction: directionTaken){
                    int[] nextCell = new int[]{cell[0]+direction[0], cell[1]+direction[1]};
                    if(!visited.contains(Arrays.toString(nextCell))){
                        visited.add(Arrays.toString(nextCell));
                        q.add(nextCell);
                    }
                }
            }
            moves++;
        }
        return -1;
    }

    int getDistance(int[] cell1, int[] cell2){
        return Math.abs(cell1[0] - cell2[0]) + Math.abs(cell1[1] - cell2[1]);
    }
}
