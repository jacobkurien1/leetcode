package main.java.TreeMapProblems;

import java.util.TreeMap;

/*
https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
Return True if its possible otherwise return False.



Example 1:

Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
Example 2:

Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
Output: true
Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
Example 3:

Input: nums = [3,3,2,2,1,1], k = 3
Output: true
Example 4:

Input: nums = [1,2,3,4], k = 3
Output: false
Explanation: Each array should be divided in subarrays of size 3.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= k <= nums.length
Note: This question is the same as 846: https://leetcode.com/problems/hand-of-straights/
 */
/*
https://leetcode.com/problems/hand-of-straights/
Alice has a hand of cards, given as an array of integers.

Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.

Return true if and only if she can.



Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
Example 2:

Input: hand = [1,2,3,4,5], W = 4
Output: false
Explanation: Alice's hand can't be rearranged into groups of 4.


Constraints:

1 <= hand.length <= 10000
0 <= hand[i] <= 10^9
1 <= W <= hand.length
Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 */
/*
Use the firstKey property of TreeMap
Running time is O(n Log(n))
Space needed is O(n)
 */
public class DivideArrayInSetsOfConsecutiveNums {
    public boolean isPossibleDivide(int[] nums, int k) {
        TreeMap<Integer, Integer> counts = new TreeMap<>();
        for(int card: nums){
            int freq = counts.getOrDefault(card, 0);
            counts.put(card, freq+1);
        }

        while(!counts.isEmpty()){
            int minVal = counts.firstKey();
            for(int i =0; i<k;i++){
                if(!counts.containsKey(minVal)){
                    return false;
                }
                int freq = counts.get(minVal);
                if(freq == 1){
                    counts.remove(minVal);
                } else{
                    counts.put(minVal, freq-1);
                }
                minVal++;
            }
        }
        return true;
    }
}
