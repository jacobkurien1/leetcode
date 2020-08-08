package ArraysProblem;

/*
https://leetcode.com/problems/move-zeroes/
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
/*
Running time is O(n)
Space needed is O(1)
 */
public class MoveZeros {
    public void moveZeroes(int[] nums) {
        int zeroSt = -1;
        for(int curr = 0; curr<nums.length; curr++){
            if(nums[curr] == 0){
                if(zeroSt == -1){
                    //first zero
                    zeroSt = curr;
                }
            } else {
                if (zeroSt != -1){
                    swap(nums, curr, zeroSt);
                    zeroSt++;
                }
            }
        }
    }

    void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
