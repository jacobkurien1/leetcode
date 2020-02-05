package ArraysProblem;

/*
https://leetcode.com/problems/first-missing-positive/
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.
 */
/*
Here we do this inline by making values in index 1->left negative.
Running time is O(n)
space is O(1)
 */
public class FirstMissingPositiveNumber {
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0){
            return 1;
        }
        int left = 0;
        int right = nums.length -1;
        while(left<=right){
            if(nums[left] <=0){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                right--;
            } else {
                left++;
            }
        }
        int maxPositiveAfterLeft = left+1;
        for(int i = 0; i<left; i++){
            int index = Math.abs(nums[i])-1;
            if(index<left){
                nums[index] = Math.abs(nums[index]) * -1;
            }
        }
        for(int i=0; i<left; i++){
            if(nums[i] > 0){
                return i+1;
            }
        }
        return maxPositiveAfterLeft;
    }
}
