package PermutationAndCombination;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/combination-sum/
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 */
/*
Running time O(n^target)
Space needed is O(target)
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        getCombinations(candidates, 0, target, combinations, new ArrayList<Integer>());
        return combinations;
    }

    void getCombinations(int[] candidates, int index, int target, List<List<Integer>> combinations, List<Integer> combination){
        if(index>=candidates.length || target<0){
            return;
        }
        if(target == 0){
            combinations.add(new ArrayList<Integer>(combination));
            return;
        }
        getCombinations(candidates, index+1, target, combinations, combination);
        combination.add(candidates[index]);
        getCombinations(candidates, index, target-candidates[index], combinations, combination);
        combination.remove(combination.size()-1);
    }
}
