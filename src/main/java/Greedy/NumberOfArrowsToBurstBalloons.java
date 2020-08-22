package Greedy;

import java.util.Arrays;

/*
https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
There are a number of spherical balloons spread in two-dimensional space.
For each balloon, provided input is the start and end coordinates of the horizontal diameter.
Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice.
Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis.
A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend.
There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely.
The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 */
/*
Sort with respect to the end times.
for each point, check whether the start of the point is before the end of the previous point.
increment arrowCount if the above condition is not met

Running time is O(nlogn)
Soace needed is O(1)
 */
public class NumberOfArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        if(points == null || points.length == 0){
            return 0;
        }
        Arrays.sort(points, (a, b)-> Integer.compare(a[1], b[1]));
        int minArrowCount = 0;
        int[] point = null;
        for(int i = 0; i<points.length; i++){
            if(point != null && points[i][0]<=point[1]){
                continue;
            }
            point = points[i];
            minArrowCount++;
        }
        return minArrowCount;
    }
}
