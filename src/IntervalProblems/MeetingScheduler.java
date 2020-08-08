package IntervalProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/meeting-scheduler/
Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration,
return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.

It is guaranteed that no two availability slots of the same person intersect with each other.
That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.



Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []


Constraints:

1 <= slots1.length, slots2.length <= 10^4
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 10^9
1 <= duration <= 10^6
 */
/*
Running time is O(nlog(n)) for sorting
Space needed is O(1)
 */
public class MeetingScheduler {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b)-> Integer.compare(a[0], b[0])); // sort w.r.t start times
        Arrays.sort(slots2, (a,b)-> Integer.compare(a[0], b[0]));
        int i1 = 0;
        int i2 = 0;
        List<Integer> ret = new ArrayList<>();
        while(i1 < slots1.length && i2<slots2.length){
            if(slots1[i1][0] >= slots2[i2][1]){
                i2++; //no intersection
            } else if(slots2[i2][0] >= slots1[i1][1] ){
                i1++; // no intersection
            } else {
                int mergeSt = Math.max(slots1[i1][0], slots2[i2][0]);
                int mergeEnd = Math.min(slots1[i1][1], slots2[i2][1]);
                if(mergeEnd-mergeSt >= duration){
                    ret.add(mergeSt);
                    ret.add(mergeSt+duration);
                    return ret;
                }else{
                    // increment for the interval which ends first
                    if(slots1[i1][1]<slots2[i2][1]){
                        i1++;
                    }else{
                        i2++;
                    }
                }
            }
        }
        return ret;
    }
}
