package Misc;

/*
https://leetcode.com/problems/robot-bounded-in-circle/
On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degress to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.



Example 1:

Input: "GGLLGG"
Output: true
Explanation:
The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
Example 2:

Input: "GG"
Output: false
Explanation:
The robot moves north indefinitely.
Example 3:

Input: "GL"
Output: true
Explanation:
The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 */
/*
Running time is O(n)
Space is O(1)
 */
public class BoundedRobot {
    public boolean isRobotBounded(String instructions) {
        final int[][] directions = new int[][] {{0, 1}, {-1, 0}, {0,-1}, {1,0}};
        if (instructions == null || instructions.length() == 0){
            return true;
        }
        int currDirection = 0;
        int x = 0;
        int y = 0;
        for(int i = 0; i<instructions.length(); i++){
            if(instructions.charAt(i) == 'R'){
                currDirection = (currDirection-1 <0)?3:currDirection-1;
            } else if (instructions.charAt(i) == 'L'){
                currDirection = (currDirection+1)%4;
            } else {
                x += directions[currDirection][0];
                y += directions[currDirection][1];
            }
        }
        return (currDirection != 0) || (x==0 && y == 0);
    }
}
