package ArraysProblem;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/intersection-of-three-sorted-arrays/
Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.



Example 1:

Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
Output: [1,5]
Explanation: Only 1 and 5 appeared in the three arrays.


Constraints:

1 <= arr1.length, arr2.length, arr3.length <= 1000
1 <= arr1[i], arr2[i], arr3[i] <= 2000
 */
/*
Running time is O(n1+n2+n3)
Space needed is O(1) if we dont consider the space needed to store the output
 */
public class IntersectionOfSortedArrays {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> ret = new ArrayList<>();
        int i1 = 0, i2 = 0, i3 = 0;
        while(i1< arr1.length && i2< arr2.length && i3 < arr3.length){
            if(arr1[i1] == arr2[i2] && arr2[i2] == arr3[i3]){
                ret.add(arr1[i1]);
            }
            int minVal = Math.min(arr1[i1], Math.min(arr2[i2], arr3[i3]));
            if(arr1[i1] == minVal){
                i1++;
            }
            if(arr2[i2] == minVal){
                i2++;
            }
            if(arr3[i3] == minVal){
                i3++;
            }
        }
        return ret;
    }
}
