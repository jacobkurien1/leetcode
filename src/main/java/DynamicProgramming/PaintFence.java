package main.java.DynamicProgramming;

/*
https://leetcode.com/problems/paint-fence
You are painting a fence of n posts with k different colors. You must paint the posts following these rules:

Every post must be painted exactly one color.
At most one pair of adjacent fence posts can have the same color.
Given the two integers n and k, return the number of ways you can paint the fence.



Example 1:


Input: n = 3, k = 2
Output: 6
Explanation: All the possibilities are shown.
Note that painting all the posts red or all the posts green is invalid because there can only be at most one pair of adjacent posts that are the same color.
Example 2:

Input: n = 1, k = 1
Output: 1
Example 3:

Input: n = 7, k = 2
Output: 42


Constraints:

1 <= n <= 50
1 <= k <= 105
The answer is guaranteed to be in the range [0, 231 - 1] for the given n and k.
 */
/*
numWays(i) = numWays_diff_paint(i) + numWays_same_paint(i)
numWays_diff_paint(i) = numWays(i-1)*(k-1)
numWays_same_paint(i) = numWays_diff_paint(i-1)*1 = numWays(i-2)*k-1
numWays(i) = numWays(i-1) * (k-1) + numWays(i-2) *(k-1)

numWays(i) = [numWays(i-1) + numWays(i-2)]* (k-1)
Running time O(n)
Space O(1)
 */
public class PaintFence {
    public int numWays(int n, int k) {
        if(n == 1){
            return k;
        }
        if(n ==2){
            return k*k;
        }
        int secondPrev = k; // for n =1
        int prev = k*k; // for n=2
        int curr =0;
        for(int i =3; i<=n; i++){
            curr = (secondPrev+prev) * (k-1);
            secondPrev = prev;
            prev = curr;
        }
        return prev;
    }
}
