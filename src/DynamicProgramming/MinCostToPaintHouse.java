package DynamicProgramming;

/*
https://leetcode.com/problems/paint-house/
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
The cost of painting each house with a certain color is different.
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
For example, costs[0][0] is the cost of painting house 0 with color red;
costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
             Minimum cost: 2 + 5 + 3 = 10.
 */
/*
Running time is O(n)
Space needed is O(1)
 */
public class MinCostToPaintHouse {
    public int minCost(int[][] costs) {
        if(costs.length == 0){
            return 0;
        }
        int[][] minPaint = new int[2][3];
        for(int i = 0;i<costs.length; i++){
            for(int j =0; j<3; j++){
                if(i-1 <0){
                    minPaint[1][j] = costs[i][j];
                } else{
                    minPaint[1][j] = Integer.MAX_VALUE;
                    for(int k = 0; k<3; k++){
                        if(k!=j){
                            minPaint[1][j] = Math.min(minPaint[1][j],
                                    minPaint[0][k] + costs[i][j]);
                        }
                    }
                }
            }
            minPaint[0] = minPaint[1];
            minPaint[1] = new int[3];
        }
        int ret = Math.min(minPaint[0][0], minPaint[0][1]);
        ret = Math.min(ret, minPaint[0][2]);
        return ret;
    }
}
