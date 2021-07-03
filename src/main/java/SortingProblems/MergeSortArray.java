package main.java.SortingProblems;
/*
https://leetcode.com/problems/sort-an-array/
Given an array of integers nums, sort the array in ascending order.



Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]


Constraints:

1 <= nums.length <= 50000
-50000 <= nums[i] <= 50000
 */
/*
Running time is O(nlogn)
Space needed is O(n) for auxillary array.
 */
public class MergeSortArray {
    public int[] sortArray(int[] nums) {
        sort(nums, 0, nums.length-1);
        return nums;
    }

    public void sort(int[] nums, int st, int end){
        if(st >= end){
            return;
        }
        int mid = st + (end-st)/2;
        sort(nums, st, mid);
        sort(nums, mid+1, end);
        merge(nums, st, mid, end);
    }

    void merge(int[] nums, int st, int mid, int end){
        if(st >= end){
            return;
        }
        int[] auc = new int[end-st+1]; // you can also have the aux array outside and hence wont have to create it all the time
        int left = st;
        int right = mid+1;
        int aucIndex = 0;
        while(left<=mid || right<=end){
            if(left>mid){
                auc[aucIndex++] = nums[right++];
            } else if(right>end){
                auc[aucIndex++] = nums[left++];
            } else if(nums[left]<=nums[right]){
                auc[aucIndex++] = nums[left++];
            } else {
                auc[aucIndex++] = nums[right++];
            }
        }

        // copy back
        for(int i = 0; i<auc.length; i++){
            nums[st++] = auc[i];
        }
    }
}
