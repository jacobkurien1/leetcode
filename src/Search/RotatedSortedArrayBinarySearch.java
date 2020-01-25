package Search;

/*
https://leetcode.com/problems/search-in-rotated-sorted-array/
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */
/*
Running time is O(log(n))
Space is O(1)
 */
public class RotatedSortedArrayBinarySearch {
    public int search(int[] nums, int target) {
        int st = 0;
        int end  = nums.length -1;
        while(st<=end){
            int mid = st + (end-st)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[mid] <nums[end]){
                if(  target < nums[mid] || target > nums[end]){
                    end = mid-1;
                } else{
                    st = mid+1;
                }
            } else {
                if(target > nums[mid] || target<= nums[end]){
                    st = mid+1;
                } else {
                    end = mid-1;
                }
            }
        }
        return -1;
    }
}
