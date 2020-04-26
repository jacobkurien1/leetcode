package ArraysProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
https://leetcode.com/problems/intersection-of-two-arrays-ii/
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
/*
Running time O(n+m)
Space is O(min(n,m))
 */
public class IntersectionOf2ArraysWithDuplicates {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        if(nums1.length > nums2.length){
            int[] temp  = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        for(int i:nums1){
            int freq = hm.getOrDefault(i, 0);
            hm.put(i, freq+1);
        }
        List<Integer> ret = new ArrayList<>();
        for(int i:nums2){
            int freq = hm.getOrDefault(i, 0);
            if(freq >0){
                ret.add(i);
                hm.put(i, freq-1);
            }
        }
        int[] retArray = new int[ret.size()];
        for(int i = 0; i<ret.size(); i++){
            retArray[i] = ret.get(i);
        }
        return retArray;
    }
}
