package main.java.SortingProblems;
/*
https://leetcode.com/problems/wiggle-sort/
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:

Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
 */
/*
Running time is O(n)
Space needed is O(1)
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        if(nums.length <=1){
            return;
        }
        boolean up = true;
        for(int i =1; i< nums.length; i++){
            if(up && nums[i-1]>nums[i] ||
                    !up && nums[i-1]<nums[i]){
                swap(nums, i-1, i);
            }
            up = !up;
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
