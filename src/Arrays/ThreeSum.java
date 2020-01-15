package Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/*
https://leetcode.com/problems/3sum
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */
/*
Running time is O(n^2 + nlogn) = o(n^2)
Space is O(n) for sorting and then storing result
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for(int i = 0; i<nums.length - 2; i++){
            if(i-1>=0 && nums[i] == nums[i-1]){
                continue; //prevents duplicate
            }
            int l = i+1;
            int r = nums.length -1;
            while(l<r){
                if(nums[l] + nums[r] == -nums[i]){
                    List<Integer> tuple = new ArrayList<>();
                    tuple.add(nums[i]);
                    tuple.add(nums[l]);
                    tuple.add(nums[r]);
                    ret.add(tuple);
                    l++; r--;
                    while(l<r && l-1>=0 && nums[l] == nums[l-1] && r+1<nums.length && nums[r] == nums[r+1]){
                        l++;r--; // prevents duplicate
                    }
                } else if(nums[l] + nums[r] > -nums[i]){
                    r--;
                } else {
                    l++;
                }
            }
        }
        return ret;
    }
}
