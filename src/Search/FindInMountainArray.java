package Search;

import java.util.HashMap;
/*
https://leetcode.com/problems/find-in-mountain-array/
(This problem is an interactive problem.)

You may recall that an array A is a mountain array if and only if:

A.length >= 3
There exists some i with 0 < i < A.length - 1 such that:
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]
Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.  If such an index doesn't exist, return -1.

You can't access the mountain array directly.  You may only access the array using a MountainArray interface:

MountainArray.get(k) returns the element of the array at index k (0-indexed).
MountainArray.length() returns the length of the array.
Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.
Also, any solutions that attempt to circumvent the judge will result in disqualification.



Example 1:

Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
Example 2:

Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.


Constraints:

3 <= mountain_arr.length() <= 10000
0 <= target <= 10^9
0 <= mountain_arr.get(index) <= 10^9
 */
/*
Running time is O(log(n))
Space needed is O(n). This can be reduced to O(1) if we dont use a cache.
 */
public class FindInMountainArray {
    HashMap<Integer, Integer> cache = new HashMap<>();
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int len  = mountainArr.length();
        int st = 0;
        int end = len-1;
        int pivotIndex = -1;
        while(st<=end){
            int mid = st + (end-st)/2;
            if(getArrValue(mountainArr, mid)> getArrValue(mountainArr, mid+1)){
                pivotIndex = mid;
                end = mid-1;
            }else{
                st = mid+1;
            }
        }

        if(pivotIndex == -1){
            return -1;
        }
        int fromLeft = getInMountainArr(mountainArr, target, 0, pivotIndex, Ordering.asc);
        if(fromLeft != -1){
            return fromLeft;
        }
        return getInMountainArr(mountainArr, target, pivotIndex+1, len-1, Ordering.desc);
    }

    int getInMountainArr(MountainArray mountainArr, int target, int st, int end, Ordering ordering) {
        if(st>end){
            return -1;
        }

        int index = -1;
        while(st<=end){
            int mid = st + (end-st)/2;
            int val = getArrValue(mountainArr, mid);
            if(val == target){
                index = mid;
                end = mid-1;
            } else if(val > target){
                if(ordering == Ordering.asc){
                    end = mid-1;
                } else {
                    st = mid+1;
                }
            } else {
                if(ordering == Ordering.asc){
                    st = mid+1;
                } else {
                    end = mid-1;
                }
            }
        }
        return index;
    }

    int getArrValue(MountainArray mountainArr, int index){
        if(!cache.containsKey(index)){
            cache.put(index, mountainArr.get(index));
        }
        return cache.get(index);
    }

    enum Ordering{
        asc,
        desc
    }
    interface MountainArray {
        public int get(int index);
        public int length();
    }
}
