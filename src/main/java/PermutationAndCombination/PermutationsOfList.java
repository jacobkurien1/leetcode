package PermutationAndCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/permutations/
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
/*
Running time is O(n*n!)
Space needed is O(n!)
 */
public class PermutationsOfList {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();

        for(int num: nums){
            ArrayList<List<Integer>> nextperms = new ArrayList<>();
            if(permutations.isEmpty()){
                nextperms.add(Arrays.asList(num));
            } else {
                for(List<Integer> permutation : permutations){
                    nextperms.addAll(getPerms(permutation, num));
                }
            }
            permutations = nextperms;
        }
        return permutations;
    }

    List<List<Integer>> getPerms(List<Integer> currPerm, int num){
        List<List<Integer>> ret = new ArrayList<>();
        for(int i = 0; i<=currPerm.size(); i++){
            List<Integer> newList = new ArrayList<>();
            newList.addAll(currPerm);
            newList.add(i, num);
            ret.add(newList);
        }
        return ret;
    }
}
