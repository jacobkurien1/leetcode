package Heaps;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/kth-largest-element-in-an-array/
Find the kth largest element in an unsorted array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
/*
Running time is O(NlogK)
space needed is O(k)

Note we can use Quick select used in Quicksort which has an avg time of O(N) and space of O(1), but the worst case is O(N^2)
 */
public class KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num : nums){
            pq.add(num);
            if(pq.size()>k){
                pq.poll();
            }
        }
        return pq.poll();
    }
}
