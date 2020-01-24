package DynamicProgramming;

/*
https://leetcode.com/problems/coin-change/
You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
 */
/*
Running time is O(amount* coins.length)
Space needed is O(amount)
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if(amount<=0 || coins == null || coins.length == 0){
            return 0;
        }
        int[] minCoins = new int[amount+1];
        for(int i = 1; i<=amount; i++){
            int minCount = Integer.MAX_VALUE;
            for(int cIndex = 0; cIndex<coins.length; cIndex++){
                if(i-coins[cIndex]>=0){
                    minCount = Math.min(minCount, minCoins[i-coins[cIndex]]);
                }
            }
            minCoins[i] = (minCount == Integer.MAX_VALUE)?Integer.MAX_VALUE:minCount+1;
        }
        return minCoins[amount]==Integer.MAX_VALUE?-1:minCoins[amount];
    }
}
