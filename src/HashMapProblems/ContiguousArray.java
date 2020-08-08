package HashMapProblems;

import java.util.HashMap;

/*
https://leetcode.com/problems/contiguous-array/
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.
 */
/*

Running time is O(n)
Space needed is O(n)
 */
public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        int maxLength = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        int curDiffCount = 0;
        hm.put(0, -1);
        for (int i = 0; i<nums.length; i++){
            if(nums[i] == 0){
                --curDiffCount;
            } else {
                ++curDiffCount;
            }
            if(!hm.containsKey(curDiffCount)){
                hm.put(curDiffCount, i);
            } else {
                int prevIndex = hm.get(curDiffCount);
                maxLength = Math.max(maxLength, i - prevIndex);
            }
        }
        return maxLength;
    }
}
