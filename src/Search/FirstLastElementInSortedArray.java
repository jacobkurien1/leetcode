package Search;
/*
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]


Constraints:

0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
nums is a non decreasing array.
-10^9 <= target <= 10^9
 */
/*
Running time is O(logn)
Space needed is O(1)
 */
public class FirstLastElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[2];

        for(int i=0; i<2; i++){
            ret[i] = gettargetIndex(i, nums, target);
        }
        return ret;
    }
    private int gettargetIndex(int i, int[] nums, int target) {
        int low = 0;
        int high = nums.length -1;
        int ret = -1;
        while (low <= high) {
            int mid = (low + high)/2;
            if(nums[mid] == target) {
                ret = mid;
                if(i==0){high = mid-1;}
                else {low = mid+1;}
            } else if (nums[mid] > target) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return ret;
    }
}
