package main.java.HashMapProblems;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

/*
https://leetcode.com/problems/diagonal-traverse-ii/
Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.


Example 1:



Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]
Example 2:



Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
Example 3:

Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
Output: [1,4,2,5,3,8,6,9,7,10,11]
Example 4:

Input: nums = [[1,2,3,4,5,6]]
Output: [1,2,3,4,5,6]


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i].length <= 10^5
1 <= nums[i][j] <= 10^9
There at most 10^5 elements in nums.
 */
/*
Running time is O(n) where n is the total number of elements in the matrix
Space needed is also O(n) where n is the total number of elements in the matrix
 */
public class TraverseDiagnals {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        HashMap<Integer, Deque<Integer>> diagMap = new HashMap<>();
        int totalElems = 0;
        int maxDiag = 0;
        for(int r=0; r<nums.size(); r++){
            for(int c= 0; c<nums.get(r).size(); c++){
                Deque<Integer> diag = diagMap.getOrDefault(r+c, new ArrayDeque());
                diag.push(nums.get(r).get(c));
                diagMap.put(r+c, diag);
                totalElems++;
                maxDiag = Math.max(maxDiag, r+c);
            }
        }

        int[] diagOrder = new int[totalElems];
        int diagIndex = 0;
        for(int i =0; i<=maxDiag; i++){
            if(diagMap.containsKey(i)){
                Deque<Integer> diag = diagMap.get(i);
                while(!diag.isEmpty()){
                    diagOrder[diagIndex++] = diag.pop();
                }
            }
        }
        return diagOrder;
    }
}
