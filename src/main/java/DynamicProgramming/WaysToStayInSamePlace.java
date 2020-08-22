package DynamicProgramming;

/*
https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/
You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left,
1 position to the right in the array or stay in the same place  (The pointer should not be placed outside the array at any time).

Given two integers steps and arrLen, return the number of ways such that your pointer still at index 0 after exactly steps steps.

Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: steps = 3, arrLen = 2
Output: 4
Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
Right, Left, Stay
Stay, Right, Left
Right, Stay, Left
Stay, Stay, Stay
Example 2:

Input: steps = 2, arrLen = 4
Output: 2
Explanation: There are 2 differents ways to stay at index 0 after 2 steps
Right, Left
Stay, Stay
Example 3:

Input: steps = 4, arrLen = 2
Output: 8


Constraints:

1 <= steps <= 500
1 <= arrLen <= 10^6
 */
/*
Here totalWays to reach a position i in the array is represented by totalWays[i]

totalWays[step][i] = totalWays[step-1][i-1] + totalWays[step-1][i] + totalWays[step-1][i+1]
return totalWays[steps][0]

Running time is O(steps*arrLen)
Space needed is O(min(steps, arrLen))
 */
public class WaysToStayInSamePlace {
    public int numWays(int steps, int arrLen) {
        int modulo = 1000000007;
        int pos = Math.min(steps, arrLen);
        if(pos == 0){
            return 0;
        }
        int[] totalWaysPrev = new int[pos];
        totalWaysPrev[0] = 1;
        totalWaysPrev[1] = 1;
        for(int i = 1;i<steps; i++){
            int[] totalWaysCurr = new int[pos];
            for(int waysIndex = 0; waysIndex<pos; waysIndex++){
                totalWaysCurr[waysIndex] = (int)(((long)((waysIndex-1>=0)?totalWaysPrev[waysIndex-1]:0) +
                        totalWaysPrev[waysIndex] + ((waysIndex+1<pos)?totalWaysPrev[waysIndex+1]:0))%modulo);
            }
            totalWaysPrev = totalWaysCurr;
        }
        return totalWaysPrev[0];
    }
}
