package DynamicProgramming;

/*
https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
Given an array arr of positive integers, consider all binary trees such that:

Each node has either 0 or 2 children;
The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
(Recall that a node is a leaf if and only if it has 0 children.)
The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.
It is guaranteed this sum fits into a 32-bit integer.



Example 1:

Input: arr = [6,2,4]
Output: 32
Explanation:
There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4


Constraints:

2 <= arr.length <= 40
1 <= arr[i] <= 15
It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
 */
/*
The running time is O(n^3)
Space requirement is O(n^2)
 */
public class MinCostTreeFromLeafValues {public int mctFromLeafValuesDPAlgo(int[] arr) {
    if(arr.length == 0){
        return 0;
    }
    int[][] nonLeafSum = new int[arr.length][arr.length];
    int[][] maxLeaf = new int[arr.length][arr.length];
    for (int k = 0; k< arr.length; k++){
        for(int st = 0; st+k<arr.length; st++){
            int end = st+k;
            if(st == end){
                maxLeaf[st][end] = arr[st];
            } else {
                nonLeafSum[st][end] = Integer.MAX_VALUE;
                for(int j = st; j<end; j++){
                    nonLeafSum[st][end] = Math.min(nonLeafSum[st][end], nonLeafSum[st][j] + nonLeafSum[j+1][end] +
                            (maxLeaf[st][j] * maxLeaf[j+1][end]));
                    maxLeaf[st][end] = Math.max(maxLeaf[st][end], arr[j]);
                }
                maxLeaf[st][end] = Math.max(maxLeaf[st][end], arr[end]);
            }
        }
    }
    return nonLeafSum[0][arr.length-1];
}

}
