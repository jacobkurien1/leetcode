package IntervalProblems;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/interval-list-intersections/
Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
The intersection of two closed intervals is a set of real numbers that is either empty,
or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

Example 1:

Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.


Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
/*
Solution: check for intersection by getting the maxStart and minEnd.
Whichever interval's end is first, increment its lists index.

Running time is O(N+M)
Space is O(N+M) as all the intervals might be perfect intersection and needs to be stored to be outputted.
 */
public class MergeTwoListOfIntervals {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int aIndex = 0;
        int bIndex = 0;
        List<Interval> ret = new ArrayList<Interval>();
        while (aIndex < A.length && bIndex <B.length){
            int maxStart = Math.max(A[aIndex][0], B[bIndex][0]);
            int minEnd = Math.min(A[aIndex][1], B[bIndex][1]);
            if(maxStart<=minEnd){
                ret.add(new Interval(maxStart, minEnd));
            }

            if(A[aIndex][1] > B[bIndex][1]){
                bIndex++;
            } else {
                aIndex++;
            }
        }

        int[][] retArr = new int[ret.size()][2];
        int index = 0;
        for(Interval interval : ret){
            retArr[index][0] = interval.st;
            retArr[index][1] = interval.end;
            index++;
        }
        return retArr;
    }

    class Interval{
        int st;
        int end;
        public Interval(int st, int end){
            this.st = st;
            this.end = end;
        }
    }
}
