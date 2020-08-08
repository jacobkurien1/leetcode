package Search;

import java.util.Arrays;

/*
https://leetcode.com/problems/find-k-th-smallest-pair-distance/
Given an integer array, return the k-th smallest distance among all the pairs.
The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Note:
2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.
 */
/*
Running time is O(n logW + nlogn) nlogn for sorting nums, W = max-min in the array
Space needed is O(1)
 */
public class KthSmallestPairDistance {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0;
        int r = nums[nums.length -1] - nums[0];
        while(l<r){
            int mid = l + (r-l)/2;
            int lessOrEqualCount = 0;
            int firstPt = 0;
            for(int i = 0; i<nums.length; i++){
                while(i>firstPt && nums[i] - nums[firstPt]>mid){
                    firstPt++;
                }
                lessOrEqualCount += i-firstPt;
            }
            if(lessOrEqualCount<k){
                l = mid+1;
            } else {
                r = mid;
            }
        }
        return r;
    }
}
