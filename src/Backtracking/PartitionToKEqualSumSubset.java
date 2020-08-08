package Backtracking;
/*
https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.



Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.


Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
 */
/*
This is dfs backtracking combination
Running time is O(k^n)
Space needed is O(n)
 */
public class PartitionToKEqualSumSubset {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        boolean[] used = new boolean[nums.length];
        int totalSum = 0;
        for(int num: nums){
            totalSum+=num;
        }
        if(totalSum%k != 0){
            return false;
        }
        return canPartitionUtil(nums, 0, k, used, 0, totalSum/k);
    }

    boolean canPartitionUtil(int[] nums, int index, int k, boolean[] used, int currSum, int totalSum){
        if(index == nums.length && k == 0){
            return true;
        }
        if(index == nums.length || currSum>totalSum){
            return false;
        }
        boolean validPartition = false;
        if(!used[index]){
            currSum += nums[index];
            used[index] = true;
            if(currSum == totalSum){
                validPartition |= canPartitionUtil(nums, 0, k-1, used, 0, totalSum);
            } else {
                validPartition |= canPartitionUtil(nums, index+1, k, used, currSum, totalSum);
            }
            //backtracking
            currSum -= nums[index];
            used[index] = false;
        }

        return validPartition || canPartitionUtil(nums, index+1, k, used, currSum, totalSum);
    }
}
