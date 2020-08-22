package ArraysProblem;

import java.util.HashSet;

/*
https://leetcode.com/problems/split-array-with-equal-sum/
Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:

0 < i, i + 1 < j, j + 1 < k < n - 1
Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.
Example:
Input: [1,2,1,2,1,2,1]
Output: True
Explanation:
i = 1, j = 3, k = 5.
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1
Note:
1 <= n <= 2000.
Elements in the given array will be in range [-1,000,000, 1,000,000].
 */
/*
Running time is O(n^2)
Space is O(n)
 */
public class SplitArrayWithEqualSum {
    public boolean splitArray(int[] nums) {
        int[] cummSum = new int[nums.length];
        for(int i = 0; i<nums.length; i++){
            cummSum[i] = nums[i] + (i>0?cummSum[i-1]:0);
        }
        for(int i = 3; i<nums.length-3; i++){
            HashSet<Integer> hs = new HashSet<Integer>();
            if(maxSumInSplitArray(nums, 0, i-1, hs, cummSum, true) &&
                    maxSumInSplitArray(nums, i+1, nums.length-1, hs, cummSum, false)){
                return true;
            }
        }
        return false;
    }

    boolean maxSumInSplitArray(int[] nums, int st, int end, HashSet<Integer> hs, int[] cummSum, boolean isLeft){
        int totalSum = cummSum[end] - ((st>0)?cummSum[st-1]:0);

        int currSum = 0;
        for(int i = st; i<=end; i++){
            if(i>st && i<end && currSum - (float)(totalSum - nums[i])/2 == 0){
                if(isLeft){
                    hs.add(currSum);
                } else {
                    if(hs.contains(currSum)){
                        return true;
                    }
                }

            }
            currSum +=nums[i];
        }
        return isLeft ? !hs.isEmpty() : false;
    }
}
