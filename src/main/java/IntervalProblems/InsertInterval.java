package main.java.IntervalProblems;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/insert-interval
57. Insert Interval
Medium

3036

256

Add to List

Share
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.



Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
Example 3:

Input: intervals = [], newInterval = [5,7]
Output: [[5,7]]
Example 4:

Input: intervals = [[1,5]], newInterval = [2,3]
Output: [[1,5]]
Example 5:

Input: intervals = [[1,5]], newInterval = [2,7]
Output: [[1,7]]


Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= intervals[i][0] <= intervals[i][1] <= 105
intervals is sorted by intervals[i][0] in ascending order.
newInterval.length == 2
0 <= newInterval[0] <= newInterval[1] <= 105
 */
/*
Running time is O(n)
Space needed is O(n)
 */
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length ==0){
            return new int[][]{newInterval};
        }
        List<int[]> merged = new ArrayList<>();
        boolean isMerged = false;
        for(int i = 0; i< intervals.length; i++){
            if(!isMerged && newInterval[1]<intervals[i][0]){
                merged.add(newInterval);
                isMerged = true;
            }
            if(!isMerged && isOverlapping(intervals[i], newInterval)){
                merged.add(new int[]{Math.min(intervals[i][0], newInterval[0]), Math.max(intervals[i][1], newInterval[1])});
                isMerged = true;
            } else if(isMerged && isOverlapping(intervals[i], merged.get(merged.size()-1))){
                int lastEnd = merged.get(merged.size()-1)[1];
                merged.get(merged.size()-1)[1] = Math.max(lastEnd, intervals[i][1]);
            } else {
                merged.add(intervals[i]);
            }
        }
        if(!isMerged){
            merged.add(newInterval);
        }
        int[][] ret = new int[merged.size()][2];
        for(int i =0; i<merged.size();i++){
            ret[i] = merged.get(i);
        }
        return ret;
    }


    boolean isOverlapping(int[] a, int[] b){
        return ((a[1]>=b[0] && a[1] <=b[1]) ||
                (b[1]>=a[0] && b[1]<=a[1]));
    }
}
