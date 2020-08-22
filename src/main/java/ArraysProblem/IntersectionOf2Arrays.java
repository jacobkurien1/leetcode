package ArraysProblem;

import java.util.HashSet;
/*
https://leetcode.com/problems/intersection-of-two-arrays/
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Note:

Each element in the result must be unique.
The result can be in any order.
 */
/*
Running time O(n+m)
Space needed is O(min(n,m)) to do this store the array in hashset which is smaller in size(not done in this solution)
 */
public class IntersectionOf2Arrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> hs = new HashSet<>();
        for(int val1:nums1){
            hs.add(val1);
        }
        HashSet<Integer> duplicates = new HashSet<Integer>();
        for(int val2:nums2){
            if(hs.contains(val2)){
                duplicates.add(val2);
            }
        }
        int[] ret = new int[duplicates.size()];
        int index = 0;
        for(int dup:duplicates){
            ret[index++] = dup;
        }
        return ret;
    }
}
