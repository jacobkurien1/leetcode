package DynamicProgramming;

/*
https://leetcode.com/problems/maximum-product-subarray/
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
/*
Running time is O(n)
Space is O(1)
 */
public class MaxProductArray {
    public int maxProduct(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int maxTillNow = nums[0];
        int minProd = nums[0];
        int maxProd = nums[0];
        for(int i =1; i<nums.length; i++) {
            if(nums[i]<0){
                int temp = minProd;
                minProd = maxProd;
                maxProd = temp;
            }

            maxProd = Math.max(nums[i], maxProd*nums[i]);
            minProd = Math.min(nums[i], minProd*nums[i]);
            maxTillNow = Math.max(maxTillNow, maxProd);
        }
        return maxTillNow;
    }
}
