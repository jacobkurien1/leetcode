package Deque;

import java.util.Deque;
import java.util.LinkedList;

/*
https://leetcode.com/problems/sliding-window-maximum/
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7]
Explanation:

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note:
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
 */
/*
Solution: Use  a datastructure like deque which we can access the first and last elements.
1. Do a sliding window on the given array and for every movement of the window, add the last element(of window)
to the last of deque after removing all the elements greater than the last element of window.
2. Now remove the element before the first element of window if that element is present in the deque. This element will
be at the first of the deque and hence you just need to check whether the first element of the deque matches to the
element we want to remove.

Also take care of the duplicates by not removing the duplicate element in 1.

The running time will be O(n) where n is nums.length
The space is O(k)
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k==0 || nums.length == 0){
            return nums;
        }
        int[] maxInSlidingWindow = new int[nums.length -k +1];
        Deque<Integer> dq = new LinkedList<Integer>();
        for(int i = 0; i<nums.length; i++){
            addMax(dq, nums[i]);
            if(i >=k){
                //completed the first sliding window, so we also need to remove
                removeVal(dq, nums[i-k]);
            }
            if(i >=k-1){
                maxInSlidingWindow[i-(k-1)] = dq.peekFirst();
            }
        }
        return maxInSlidingWindow;
    }

    void addMax(Deque<Integer> dq, int valToInsert){
        while(dq.size()>0 && dq.peekLast() < valToInsert){
            dq.removeLast();
        }
        dq.addLast(valToInsert);
    }

    void removeVal(Deque<Integer> dq, int valToRemove){
        if(dq.size()!=0 && dq.peekFirst() == valToRemove){
            dq.removeFirst();
        }
    }
}
