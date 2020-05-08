package DynamicProgramming;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
/*
Running time is O(n)
Space needed is O(n)
 */
public class StockProfitWith2Trades {
    /*
    currProfit[i] = Math.max(currProfit[i-1], prevProfit[t] + prices[i] - prices[t])
                   where t -> o to i
   localMax can be used to get max of prevProfit[t] -prices[t]
   */
    public int maxProfit(int[] prices) {
        if(prices.length <=1){
            return 0;
        }
        int[] prevProfit = new int[prices.length];
        for(int transaction = 1; transaction<=2; transaction++){
            int[] currProfit = new int[prices.length];
            int localMax = Integer.MIN_VALUE;
            for(int i=0; i<prices.length;i++){
                localMax = Math.max(localMax, prevProfit[i] -prices[i]);
                currProfit[i] = Math.max(((i-1>=0)?currProfit[i-1]:0), localMax + prices[i]);
            }
            prevProfit = currProfit;
        }
        return prevProfit[prices.length-1];
    }
}
