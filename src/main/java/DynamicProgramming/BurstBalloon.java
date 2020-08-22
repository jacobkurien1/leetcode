package DynamicProgramming;

/*
https://leetcode.com/problems/burst-balloons/
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
/*
This is a dynamic programming problem. Here we have done a bottom up solution
Here we start with single balloons and increase the number of balloons (k) considered.
In the number of balloons considered, calculate the maxPoints when j is the last balloon which gets burst.
maxPOints(st, end) = Math.max (j-> st to end{num[j]*nums[st-1]*nums[end+1] + maxPoints(st, j-1) + maxPoints(j+1, end})

Running time is O(n^3)
Space is O(n^2)
 */
public class BurstBalloon {
    public int maxPoints(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int[][] dp = new int[nums.length][nums.length];
        for(int k = 0; k<nums.length; k++){
            for(int st = 0; st+k<nums.length; st++){
                int end = st + k;
                int maxPoints = 0;
                for(int j = st; j<=end; j++){
                    // Burst balloon at j
                    int curr = nums[j];
                    int currMinus1 = ((st-1)<0)?1:nums[st-1];
                    int currPlus1 = ((end+1)>=nums.length)?1:nums[end+1];
                    maxPoints = Math.max(maxPoints, curr * currMinus1 * currPlus1 +
                            (((j-1)<st)?0:dp[st][j-1]) +
                            (((j+1)>end)?0:dp[j+1][end]));
                }
                dp[st][end] = maxPoints;
            }
        }
        return dp[0][nums.length-1];
    }
}
