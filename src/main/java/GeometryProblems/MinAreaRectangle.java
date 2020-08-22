package GeometryProblems;

import java.util.Arrays;
import java.util.HashSet;

/*
https://leetcode.com/problems/minimum-area-rectangle/
Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.



Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2


Note:

1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.
 */
/*
get 2 points which are diagonal and check whether other points are present.
Running time is O(n^2)
Space needed is O(n)
 */
public class MinAreaRectangle {
    public int minAreaRect(int[][] points) {
        HashSet<String> ptSet = new HashSet<>();
        for(int[] point:points){
            ptSet.add(Arrays.toString(point));
        }

        int minArea = Integer.MAX_VALUE;
        for(int i = 0; i<points.length; i++){
            for(int j = i+1; j<points.length; j++){
                if(points[i][0] == points[j][0] || points[i][1] == points[j][1]){
                    continue; // cannot be diagonal of rectangle
                }
                int[] pt1  = new int[]{points[i][0], points[j][1]};
                int[] pt2 = new int[]{points[j][0], points[i][1]};
                if(ptSet.contains(Arrays.toString(pt1)) && ptSet.contains(Arrays.toString(pt2))){
                    minArea = Math.min(minArea, ((int)Math.abs(points[i][1] - pt1[1]) * (int)Math.abs(points[j][0] - pt1[0])));
                }
            }
        }
        return (minArea == Integer.MAX_VALUE ?0:minArea);
    }
}
