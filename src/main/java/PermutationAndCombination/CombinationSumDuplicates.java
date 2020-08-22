package PermutationAndCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/combination-sum-ii/
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
 */
/*
Running time is O(n^target)
Space is O(target)
 */
public class CombinationSumDuplicates {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        Arrays.sort(candidates);
        getCombinations(candidates, 0, target, combinations, new ArrayList<>());
        return combinations;
    }

    void getCombinations(int[] candidates, int index, int target, List<List<Integer>> combinations, List<Integer> combination){
        if(target == 0){
            combinations.add(new ArrayList<Integer>(combination));
            return;
        }
        if(index >= candidates.length || target<0){
            return;
        }

        if(index+1<candidates.length && candidates[index] != candidates[index+1]){
            getCombinations(candidates, index+1, target, combinations, combination);
        }
        combination.add(candidates[index]);
        getCombinations(candidates, index+1, target-candidates[index], combinations, combination);
        combination.remove(combination.size()-1);
    }
}
