package DynamicProgramming;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
Say you have an array for which the i-th element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */
/*
Running time O(k*n)
Space needed is O(n)
 */
public class StockProfitWithKTrades {
    public int maxProfit(int k, int[] prices) {

        if(k>=prices.length/2){
            // find the max Profit with max transactions
            int maxProfit = 0;
            int minStock = Integer.MAX_VALUE;
            for(int i = 0; i<prices.length; i++){
                minStock = Math.min(minStock, prices[i]);
                if(prices[i]>minStock){
                    maxProfit += prices[i] - minStock;
                    minStock = prices[i];
                }
            }
            return maxProfit;
        }

        /*
        currTransaction[i] =Max(currTransaction[i-1], Take Max of { m -> 0 to i (prevTransaction[m] + prices[i]-prices[m] )})
        currTransaction[i-1] - > when we dont take price[i] in our transaction
        max of (prevTransaction[m] - prices[m]) can be collected as we traverse
        */

        int[] prevTransaction = new int[prices.length];
        for(int transaction = 1; transaction<=k; transaction++){
            int[] currTransaction = new int[prices.length];
            int localMax = Integer.MIN_VALUE;
            for(int i = 0; i<prices.length;i++){
                localMax = Math.max(localMax, prevTransaction[i]-prices[i]);
                currTransaction[i] = Math.max((i-1>=0)?currTransaction[i-1]:0, localMax + prices[i]);
            }
            prevTransaction = currTransaction;
        }
        return prevTransaction[prices.length-1];
    }
}
