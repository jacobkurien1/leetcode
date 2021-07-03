package main.java.DynamicProgramming;

/*
https://leetcode.com/problems/house-robber-ii/
213. House Robber II
Medium

2992

66

Add to List

Share
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.



Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
Example 2:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 3:

Input: nums = [0]
Output: 0


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
 */
/*
Running time is O(n)
Space O(1)
 */
public class HouseRobber {
    public int rob(int[] nums) {
        if(nums.length <= 2){
            return maxrob(nums, 0, nums.length-1);
        }
        return Math.max(maxrob(nums, 0, nums.length-2), maxrob(nums,1, nums.length-1));
    }

    int maxrob(int[] nums, int st, int end){
        int secondLast = 0;
        int last = 0;
        int max = 0;
        for(int i =st; i<= end; i++){
            max = Math.max(secondLast+nums[i], last);
            secondLast = last;
            last = max;
        }
        return last;
    }
}
