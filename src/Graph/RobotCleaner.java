package Graph;

import java.util.Arrays;
import java.util.HashSet;

/*
https://leetcode.com/problems/robot-room-cleaner/
Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}
Example:

Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.
Notes:

The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded".
In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
The robot's initial position will always be in an accessible cell.
The initial direction of the robot will be facing up.
All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
Assume all four edges of the grid are all surrounded by wall.
 */
/*
Running time is O(N)
Space time O(N) where N is the number of cells in the room
 */
public class RobotCleaner {
    public void cleanRoom(Robot r) {
        HashSet<String> visited = new HashSet<>();
        dfs(r, 0,0, visited, 0);
    }

    int[] directions = new int[]{0,90, 180,270};
    void dfs(Robot r, int x, int y, HashSet<String> visited, int rotation){
        r.clean();
        visited.add(Arrays.toString(new int[]{x, y}));
        for(int direction: directions){
            int nextRotation = (rotation+direction)%360;
            int[] nextCell = getNextCell(x, y, nextRotation);
            if(!visited.contains(Arrays.toString(nextCell)) && r.move()){
                dfs(r, nextCell[0], nextCell[1], visited, nextRotation);
                moveToPrevCell(r); // going back to cell from which we came here and also being in right rotation
            }
            r.turnRight(); //point to the next direction
        }
    }

    void moveToPrevCell(Robot r){
        r.turnLeft();
        r.turnLeft();
        r.move();
        r.turnRight();
        r.turnRight();
    }

    int[] getNextCell(int x, int y, int rotation){
        if(rotation == 0){
            y++;
        } else if(rotation == 90){
            x++;
        } else if(rotation == 180){
            y--;
        } else {
            x--;
        }
        return new int[]{x,y};
    }

    // This is the robot's control interface.
    // You should not implement it, or speculate about its implementation
    interface Robot {
      // Returns true if the cell in front is open and robot moves into the cell.
      // Returns false if the cell in front is blocked and robot stays in the current cell.
      public boolean move();

      // Robot will stay in the same cell after calling turnLeft/turnRight.
      // Each turn will be 90 degrees.
      public void turnLeft();
      public void turnRight();

      // Clean the current cell.
      public void clean();
    }
}
