package ArraysProblem;

import java.util.Arrays;

/*
https://leetcode.com/problems/3sum-closest/
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
/*
Running time is O(n^2)
Space needed is O(n) for sorting
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int closestSum = Integer.MAX_VALUE;
        if(nums.length<3){
            return closestSum;
        }
        Arrays.sort(nums);
        for(int i = 0; i<nums.length-1; i++){
            if(i!=0 && nums[i] == nums[i-1]){
                continue; // optimization for duplicates
            }
            int newTarget = target -nums[i];
            int l = i+1;
            int r = nums.length-1;
            while(l<r){
                if(closestSum == Integer.MAX_VALUE ||
                        Math.abs(target - closestSum) >Math.abs(target - (nums[i] + nums[l] + nums[r]))){
                    closestSum = (nums[i] + nums[l] + nums[r]);
                }
                if(newTarget - (nums[l] + nums[r]) == 0){
                    return target;
                } else if (newTarget -(nums[l] + nums[r]) >0){
                    l++;
                } else {
                    r--;
                }
            }
        }
        return closestSum;
    }
}
