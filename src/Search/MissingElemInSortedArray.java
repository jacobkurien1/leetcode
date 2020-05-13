package Search;

/*
https://leetcode.com/problems/single-element-in-a-sorted-array/
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.



Example 1:

Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: [3,3,7,7,10,11,11]
Output: 10


Note: Your solution should run in O(log n) time and O(1) space.
 */
/*
Running time is O(logn)
Space is O(1)
 */
public class MissingElemInSortedArray {
    public int singleNonDuplicate(int[] nums) {
        int st = 0;
        int end = nums.length-1;
        while(st<=end){
            int mid = st + (end-st)/2;
            if(mid-1>=0 && nums[mid] == nums[mid-1]){
                mid = mid-1;
            } else if(mid+1>=nums.length || nums[mid] != nums[mid+1]){
                return nums[mid];
            }
            if((mid-end+1)%2 == 0){
                end = mid-1;//go left
            } else{
                st = mid+2; // go right
            }
        }
        return -1;
    }
}
