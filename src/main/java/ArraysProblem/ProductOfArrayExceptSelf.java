package ArraysProblem;

/*
https://leetcode.com/problems/product-of-array-except-self/
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
/*
We can do this in 2 pass
a        b        c         d

1        a        ab        abc ---> ret[i-1]*nums[i-1]
bcd      cd       d         1  <---- prod*nums[i+1]

 */
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] ret = new int[nums.length];
        ret[0] = 1;
        for(int i=1;i<nums.length;i++){
            ret[i] = ret[i-1]*nums[i-1];
        }
        int prod = 1;
        for(int i=nums.length-2; i>=0;i--){
            prod *= nums[i+1];
            ret[i] *= prod;
        }
        return ret;
    }
}
