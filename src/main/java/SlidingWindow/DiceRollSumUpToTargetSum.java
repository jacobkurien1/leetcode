package main.java.SlidingWindow;
/*
https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
You have d dice, and each die has f faces numbered 1, 2, ..., f.

Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.



Example 1:

Input: d = 1, f = 6, target = 3
Output: 1
Explanation:
You throw one die with 6 faces.  There is only one way to get a sum of 3.
Example 2:

Input: d = 2, f = 6, target = 7
Output: 6
Explanation:
You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
Example 3:

Input: d = 2, f = 5, target = 10
Output: 1
Explanation:
You throw two dice, each with 5 faces.  There is only one way to get a sum of 10: 5+5.
Example 4:

Input: d = 1, f = 2, target = 3
Output: 0
Explanation:
You throw one die with 2 faces.  There is no way to get a sum of 3.
Example 5:

Input: d = 30, f = 30, target = 500
Output: 222616187
Explanation:
The answer must be returned modulo 10^9 + 7.


Constraints:

1 <= d, f <= 30
1 <= target <= 1000
 */
public class DiceRollSumUpToTargetSum {
    /**
     * Algo1:
     * We can use DP to solve this problem
     * Running time for this approach is O(dices * faces * target)
     * Spaced needed is O(target)
     * @param dices
     * @param faces
     * @param target
     * @return
     */
    public int numRollsToTargetDP(int dices, int faces, int target) {
        if(dices == 0){
            return 0;
        }
        int MODULO = 1000000007;
        int[] lastRoll = new int[target+1];
        for(int i = 1; i<=Math.min(target, faces); i++){
            lastRoll[i] = 1;
        }
        --dices;

        for(int d = 0; d<dices; d++){
            int[] currRoll = new int[target+1];
            for(int t = d; t<=target; t++){
                for(int f = 1; f<=faces; f++){
                    if(t-f>=1){
                        currRoll[t] += lastRoll[t-f];
                        currRoll[t] %= MODULO;
                    }
                }
            }
            lastRoll = currRoll;
        }
        return lastRoll[target];
    }

    /**
     * Algo2:
     * We can use dp and sliding window to further reduce the running time
     * Running time O(dices* target)
     * Space needed is O(target)
     * @param dices
     * @param faces
     * @param target
     * @return
     */
    public int numRollsToTarget(int dices, int faces, int target) {
        if(dices == 0){
            return 0;
        }
        int MODULO = 1000000007;
        int[] lastRoll = new int[target+1];
        for(int i = 1; i<=Math.min(target, faces); i++){
            lastRoll[i] = 1;
        }
        --dices;

        for(int d = 0; d<dices; d++){
            int[] currRoll = new int[target+1];
            long windowSum = 0;
            int st =1;
            // we do sliding window here
            for(int end =1; end<target; end++){
                long curr = 0;
                if(end-st+1 > faces){
                    curr -=lastRoll[st];
                    st++;
                }
                curr += lastRoll[end];
                curr %= MODULO;
                windowSum += curr;
                currRoll[end+1] = (int)(windowSum%MODULO);
            }
            lastRoll = currRoll;
        }
        return lastRoll[target];
    }
}
