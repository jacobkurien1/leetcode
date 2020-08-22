package DynamicProgramming;

import java.util.HashMap;

/*
https://leetcode.com/problems/unique-binary-search-trees
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */
/*
Running time O(n)
space O(n)
 */
public class NumberOfUniqueBST {
    /*
    Algo 1: iterative
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i<=n; i++){
            for(int j = 1;j<=i; j++){
                dp[i] += (dp[j-1])*(dp[i-j]);
            }
        }
        return dp[n];
    }
    /*
    Algo1: End
     */

    /*
    Algo2: recursion
     */
    public int numTreesAlgo2(int n) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        return numTreesUtil(n, map);
    }

    int numTreesUtil(int n, HashMap<Integer, Integer> map){
        if(n == 0 || n==1 || n == 2){
            return n;
        }
        if(map.containsKey(n)){
            return map.get(n);
        }
        int numTrees = 0;
        for(int i = 1; i<=n; i++){
            int treesLeft = numTreesUtil(i-1, map);
            int treesRight = numTreesUtil(n-i, map);
            numTrees += ((treesLeft == 0)?1:treesLeft) * ((treesRight == 0)?1:treesRight);
        }
        map.put(n, numTrees);
        return numTrees;
    }
    /*
    Algo2: End
     */
}
