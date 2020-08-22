package Heaps;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/minimum-cost-to-connect-sticks/
You have some sticks with positive integer lengths.

You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.
You perform this action until there is one stick remaining.

Return the minimum cost of connecting all the given sticks into one stick in this way.



Example 1:

Input: sticks = [2,4,3]
Output: 14
Example 2:

Input: sticks = [1,8,3,5]
Output: 30


Constraints:

1 <= sticks.length <= 10^4
1 <= sticks[i] <= 10^4
 */
/*
Running time is O(nlog(n))
Space is O(n)
 */
public class MinCostToConnectSticks {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int stick : sticks){
            pq.add(stick);
        }
        int minCost = 0;
        while(pq.size()>1){
            int val1 = pq.poll();
            int val2 = pq.poll();
            minCost += val1+val2;
            pq.add(val1+val2);
        }
        return minCost;
    }
}
