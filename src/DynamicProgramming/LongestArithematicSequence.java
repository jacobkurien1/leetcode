package DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;

/*
https://leetcode.com/problems/longest-arithmetic-sequence/
Given an array A of integers, return the length of the longest arithmetic subsequence in A.

Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).



Example 1:

Input: [3,6,9,12]
Output: 4
Explanation:
The whole array is an arithmetic sequence with steps of length = 3.
Example 2:

Input: [9,4,7,2,10]
Output: 3
Explanation:
The longest arithmetic subsequence is [4,7,10].
Example 3:

Input: [20,1,15,3,10,5,8]
Output: 4
Explanation:
The longest arithmetic subsequence is [20,15,10,5].


Note:

2 <= A.length <= 2000
0 <= A[i] <= 10000
 */
public class LongestArithematicSequence {
    /**************************************
     * Algo1; With Hashmap<Integer, int[]>
     *     Running time is O(n^2)
     *     Space is O(n^2)
     * @param A
     * @return
     */
    public int longestArithSeqLength(int[] A) {
        if(A == null || A.length == 0){
            return 0;
        }
        HashMap<Integer, int[]> hm = new HashMap<>();
        int maxArithSeqLen = 1;
        for(int i = 1;i<A.length;i++){
            for(int j =0;j<i;j++){
                int diff = A[i] - A[j];
                if(!hm.containsKey(diff)){
                    hm.put(diff, getMaxLenArray(A.length));
                }
                int[] maxArr = hm.get(diff);
                maxArr[i] = Math.max(maxArr[i], maxArr[j]+1);
                maxArithSeqLen = Math.max(maxArr[i], maxArithSeqLen);
            }
        }
        return maxArithSeqLen;
    }

    int[] getMaxLenArray(int size){
        int[] maxLen = new int[size];
        Arrays.fill(maxLen, 1);
        return maxLen;
    }


    /***********************************************
     * Algo2: Takes lesser space in avg case
     * By using HashMap<Integer, HashMap>
     * Running time is O(n^2)
     * Space needed is O(n^2)
     */
    public int longestArithSeqLengthAlgo2(int[] A) {
        if(A == null || A.length == 0){
            return 0;
        }
        HashMap<Integer, HashMap<Integer, Integer>> hm = new HashMap<>();
        int maxArithSeqLen = 1;
        for(int i = 1;i<A.length;i++){
            for(int j =0;j<i;j++){
                int diff = A[i] - A[j];
                if(!hm.containsKey(diff)){
                    hm.put(diff, new HashMap<>());
                }
                HashMap<Integer, Integer> diffLevelHm = hm.get(diff);
                int lengthAtJ = diffLevelHm.getOrDefault(j, 1);
                int lengthAtI = diffLevelHm.getOrDefault(i,1);
                diffLevelHm.put(i, Math.max(lengthAtI, lengthAtJ+1));
                maxArithSeqLen = Math.max(diffLevelHm.get(i), maxArithSeqLen);
            }
        }
        return maxArithSeqLen;
    }
}
