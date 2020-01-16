package PermutationAndCombination;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/subsets/
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */
/*
Running time is O(N*2^N) 2^N since each element can be present or absent and N for doing N deep clones.
Space complexity is O(2^N)
 */
public class Subset {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<List<Integer>>();
        subsets.add(new ArrayList<Integer>());
        for(int elem : nums){
            List<List<Integer>> newList = new ArrayList<List<Integer>>();
            for(List<Integer> subset : subsets){
                List<Integer> clone= getClone(subset);
                clone.add(elem);
                newList.add(clone);
            }
            for(List<Integer> newSubset : newList){
                subsets.add(newSubset);
            }
        }
        return subsets;
    }

    List<Integer> getClone(List<Integer> arrList){
        return new ArrayList<Integer>(arrList);
    }
}
