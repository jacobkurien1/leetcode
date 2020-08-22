package DynamicProgramming;

/*
https://leetcode.com/problems/split-array-largest-sum/
Given an array which consists of non-negative integers and an integer m,
 you can split the array into m non-empty continuous subarrays.
 Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 */
/*
maxSum[m, n] = Math.min for all k(Math.max(maxSum[m-1,k-1], cummulativeSum[k,n]))
Running time is O(m*n^2)
Space is O(n)
 */
public class SplitArrayMinimizeLargestSum {
    public int splitArray(int[] nums, int m) {
        if(m<=0 || nums == null || nums.length == 0){
            return 0;
        }
        int[] maxPrevSum = new int[nums.length];
        int[] maxCurrSum = new int[nums.length];
        for(int i = 0; i<nums.length; i++){
            maxPrevSum[i] = (i==0)?nums[i]:maxPrevSum[i-1]+nums[i];
        }
        for(int row = 2; row<=m; row++){
            for(int col = 0; col<nums.length; col++){
                int cummSum = 0;
                maxCurrSum[col] = Integer.MAX_VALUE;
                for(int k = col; k>=row-1 ;k-- ){
                    cummSum += nums[k];
                    maxCurrSum[col] = Math.min(maxCurrSum[col], Math.max(cummSum, maxPrevSum[k-1]));
                }
            }
            maxPrevSum = maxCurrSum;
            maxCurrSum = new int[nums.length];
        }
        return maxPrevSum[nums.length-1];
    }
}
