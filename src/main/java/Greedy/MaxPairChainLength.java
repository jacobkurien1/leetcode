package main.java.Greedy;

import java.util.Arrays;

/*
https://leetcode.com/problems/maximum-length-of-pair-chain/
You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]
Note:
The number of given pairs will be in the range [1, 1000].
 */
public class MaxPairChainLength {
    /***
     * This is a greedy approach with O(Nlogn).
     * This is the optimal approach
     * Steps are:
     * sort by ends of the pair
     * now check if the pair's start is greater than the last end
     * if yes update lastEnd and increment longestChainCount
     *
     * space needed is O(N) sorting can use O(N) space.
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        if (pairs.length == 0){
            return 0;
        }
        Arrays.sort(pairs, (a, b)-> Integer.compare(a[1], b[1]));
        int lastEnd = Integer.MIN_VALUE;
        int longestChainCount = 0;
        for(int[] pair:pairs){
            if(pair[0]>lastEnd){
                lastEnd = pair[1];
                ++longestChainCount;
            }
        }
        return longestChainCount;
    }


    /***
     * This is a dynamic programming approach
     * Running time is O(n^2)
     * Space needed is O(n)
     * @param pairs
     * @return
     */
    public int findLongestChainDP(int[][] pairs) {
        if (pairs.length == 0){
            return 0;
        }
        Arrays.sort(pairs, (a, b)-> Integer.compare(a[0], b[0]));
        int[] longestChain = new int[pairs.length];
        for(int i = 0; i< pairs.length; i++){
            longestChain[i] = 1;
            for(int j = 0; j<i; j++){
                if(pairs[j][1] < pairs[i][0]){
                    longestChain[i] = Math.max(longestChain[i], longestChain[j]+1);
                }
            }
        }
        return longestChain[pairs.length-1];
    }
}
