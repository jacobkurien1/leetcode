package Misc;

import java.util.Arrays;

/*
https://leetcode.com/problems/valid-triangle-number/
Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are:
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].
 */
/*
Running time is O(n^2)
Space is O(logn) for sorting
 */
public class ValidTriangleCount {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for(int i =0;i<nums.length;i++){
            int j = i+1;
            int k = j+1;
            while(k<nums.length){
                if(nums[i] + nums[j] >nums[k]){
                    count += k-j;
                    k++;
                } else{
                    j++;
                    if(j == k){
                        k++;
                    }
                }

            }
        }
        return count;
    }
}
