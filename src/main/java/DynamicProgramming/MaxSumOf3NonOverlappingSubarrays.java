package DynamicProgramming;

/*
https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:

Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.


Note:

nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).
 */
/*
Running time is O(n)
Space needed is O(n)
 */
public class MaxSumOf3NonOverlappingSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] cummSum = new int[nums.length];
        for(int i = 0; i<nums.length; i++){
            cummSum[i] = nums[i] + ((i-1>=0)?cummSum[i-1]:0);
        }

        // DP to get the left subarray
        int[] leftMax = new int[nums.length];
        int[] leftIndex = new int[nums.length];
        for(int i = k-1; i<nums.length; i++){
            int kSumTillI = cummSum[i] - ((i==k-1)?0:cummSum[i-k]);
            if(i-1>=0 && leftMax[i-1]>=kSumTillI){
                leftMax[i] = leftMax[i-1];
                leftIndex[i] = leftIndex[i-1];
            } else{
                leftMax[i] = kSumTillI;
                leftIndex[i] = i-k+1;
            }
        }

        // DP to get the right subarray
        int[] rightMax = new int[nums.length];
        int[] rightIndex = new int[nums.length];
        for(int i = nums.length-k; i>=0; i--){
            int kSumFromRight = cummSum[i+k-1] - ((i-1>=0)?cummSum[i-1]:0);
            if(i==nums.length-k || kSumFromRight >= rightMax[i+1]){
                rightMax[i] = kSumFromRight;
                rightIndex[i] = i;
            } else{
                rightMax[i]= rightMax[i+1];
                rightIndex[i] = rightIndex[i+1];
            }
        }

        // consider all cases for center subarray
        int maxSum = 0;
        int[] maxSumSt = new int[3];
        for(int i = k; i<=nums.length-(2*k);i++){
            int leftSum = leftMax[i-1];
            int rightSum = rightMax[i+k];
            int centerSum = cummSum[i+k-1] - cummSum[i-1];
            if(maxSum< leftSum+rightSum+centerSum){
                maxSum = leftSum+rightSum+centerSum;
                maxSumSt = new int[]{leftIndex[i-1], i, rightIndex[i+k]};
            }
        }
        return maxSumSt;
    }
}
