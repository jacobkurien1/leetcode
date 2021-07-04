package PatienceSort;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/longest-increasing-subsequence/
Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].



Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1



Constraints:

    1 <= nums.length <= 2500
    -104 <= nums[i] <= 104




 */
/*
we can do dynamic programming here, but the running time will be O(n^2)
We can do better via Patience sort.
The idea is to have piles of card. A new pile is created when the next card is the biggest from all the previous ones.
In other case find the pile that is greater or equal to the current card and put the current card in that pile.
Tails list denotes the smallest tail card of the pile.
The running time is O(nlog(n)) since the tails array stores the last card of the pile and is sorted.
Space needed is O(n)
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        for(int i =0;i< nums.length; i++){
            if(tails.size() == 0 || tails.get(tails.size()-1) < nums[i]){
                tails.add(nums[i]);
            } else {
                int st = 0;
                int end = tails.size()-1;
                int index = 0;
                while(st<=end){
                    int mid = st + (end-st)/2;
                    if(tails.get(mid)>=nums[i]){
                        index = mid;
                        end = mid-1;
                    } else {
                        st = mid+1;
                    }
                }
                tails.set(index, nums[i]);
            }
        }
        return tails.size();
    }
}
