package ArraysProblem;

/*
https://leetcode.com/problems/maximum-subarray/
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int maxVal = Integer.MIN_VALUE;
        int currSum = 0;
        for(int n :nums){
            currSum += n;
            maxVal = Math.max(maxVal, currSum);
            if(currSum <0){
                currSum = 0;
            }
        }
        return maxVal;
    }
}