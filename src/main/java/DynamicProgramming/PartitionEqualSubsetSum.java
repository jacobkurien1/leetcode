package DynamicProgramming;

/*
https://leetcode.com/problems/partition-equal-subset-sum/
Given a non-empty array containing only positive integers,
find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.


Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].


Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
 */
/*
This is a knap sack problem if the weights is not too big.
Running time is O(nums.length*weights)
Space needed is O(weights)
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        if(nums.length<=1){
            return false;
        }
        int cummSum = 0;
        for(int num:nums){
            cummSum += num;
        }
        if(cummSum%2 ==1){
            return false;
        }
        // Optimized space for Knap-Sack problem
        boolean[] prev = new boolean[cummSum/2+1];
        prev[0] = true;
        for(int i = 0; i<nums.length; i++){
            boolean[] curr = new boolean[cummSum/2+1];
            for(int wt = 1; wt<=cummSum/2; wt++){
                curr[wt] = prev[wt]; // when we dont pick nums[i]
                if(wt - nums[i]>=0){
                    curr[wt] |= prev[wt-nums[i]]; //when we pick nums[i]
                }
            }
            prev = curr;
        }

        return prev[cummSum/2];
    }

    /*
    Algo2: if Space needed for weights is too much we can do a backtracking solution with
    Running time O(2^n)
    Space needed is O(2^n)
     */
    public boolean canPartitionBacktracking(int[] nums) {
        if(nums.length<=1){
            return false;
        }

        int cummSum = 0;
        for(int num: nums){
            cummSum+=num;
        }
        if(cummSum%2==1){
            return false;
        }
        return getPartition(nums, 0, cummSum/2);
    }

    boolean getPartition(int[] nums, int index, int sum){
        if(sum == 0){
            return true;
        }
        if(index>=nums.length || sum<0){
            return false;
        }

        return getPartition(nums, index+1,sum - nums[index]) ||
                getPartition(nums, index+1, sum );
    }
}
