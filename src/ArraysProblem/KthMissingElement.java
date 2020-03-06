package ArraysProblem;

/*
https://leetcode.com/problems/missing-element-in-sorted-array/
Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.



Example 1:

Input: A = [4,7,9,10], K = 1
Output: 5
Explanation:
The first missing number is 5.
Example 2:

Input: A = [4,7,9,10], K = 3
Output: 8
Explanation:
The missing numbers are [5,6,8,...], hence the third missing number is 8.
Example 3:

Input: A = [1,2,4], K = 3
Output: 6
Explanation:
The missing numbers are [3,5,6,7,...], hence the third missing number is 6.


Note:

1 <= A.length <= 50000
1 <= A[i] <= 1e7
1 <= K <= 1e8
 */
/*
Running time is O(n)
Space is O(1)
 */
public class KthMissingElement {
    public int missingElement(int[] nums, int k) {
        if(nums.length ==0){
            return k;
        }
        for(int i = 1; i<nums.length; i++){
            int missingNumsCount = nums[i] - nums[i-1] -1;
            if (missingNumsCount < k){
                k -= missingNumsCount;
            } else {
                return nums[i-1] + k;
            }
        }
        return nums[nums.length-1] + k;
    }
}
