package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/target-sum/
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3.
Output: 5
Explanation:

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
/*
Running time is O(l*N)
Space needed is O(l)
where l 2001 as the sum will not exceed 1000
n is the number of elements in nums
 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        HashMap<Integer, Integer> prevWaysCount = new HashMap<Integer, Integer>();
        for(int i = 0; i<nums.length; i++){
            HashMap<Integer, Integer> waysCount = new HashMap<>();
            if(i == 0){
                waysCount.put(nums[i], 1);
                waysCount.put(-1*nums[i], waysCount.getOrDefault(-1*nums[i], 0)+1); // to fix the case when we have 0 so +0 and -0 should be counted seperately
            } else {
                for(Map.Entry<Integer, Integer> prevEntry:prevWaysCount.entrySet()){
                    int positiveSum = prevEntry.getKey() + nums[i];
                    waysCount.put(positiveSum, waysCount.getOrDefault(positiveSum, 0) + prevEntry.getValue());
                    int negativeSum = prevEntry.getKey() - nums[i];
                    waysCount.put(negativeSum, waysCount.getOrDefault(negativeSum, 0) + prevEntry.getValue());
                }
            }
            prevWaysCount = waysCount;
        }
        return prevWaysCount.getOrDefault(S, 0);
    }
}
