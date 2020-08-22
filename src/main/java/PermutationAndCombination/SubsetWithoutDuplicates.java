package PermutationAndCombination;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/*
https://leetcode.com/problems/subsets-ii
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */
/*
Running time is O(N*2^N) 2^N since each element can be present or absent and N for doing N deep clones.
Space complexity is O(2^N)
 */
public class SubsetWithoutDuplicates {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums.length == 0){
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> subsets = new ArrayList<List<Integer>>();
        subsets.add(new ArrayList());
        Arrays.sort(nums);
        int prevSubsetsSize = 0;
        for(int i = 0; i<nums.length; i++){
            List<List<Integer>> partialSubsets = new ArrayList<List<Integer>>();
            for(int subsetIndex = (i-1>=0 && nums[i-1] == nums[i])?prevSubsetsSize:0;
                subsetIndex<subsets.size(); subsetIndex++){
                List<Integer> subsetClone = new ArrayList<Integer>(subsets.get(subsetIndex));
                subsetClone.add(nums[i]);
                partialSubsets.add(subsetClone);
            }
            prevSubsetsSize = subsets.size();
            for(List<Integer> subsetToAdd : partialSubsets){
                subsets.add(subsetToAdd);
            }
        }
        return subsets;
    }
}
