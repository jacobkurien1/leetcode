package Arrays;

/*
https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
Given an integer n, return any array containing n unique integers such that they add up to 0.



Example 1:

Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
Example 2:

Input: n = 3
Output: [-1,0,1]
Example 3:

Input: n = 1
Output: [0]


Constraints:

1 <= n <= 1000
 */
/*
Solution: if n is odd then add 0,
And then keep adding +1,-1, +2,-2, .. till the array is filled
 */
public class SumZeroArray {
    public int[] sumZero(int n) {
        if(n<=0){
            return null;
        }
        int[] sumZeroArray = new int[n];
        int arrIndex = 0;
        int val = 1;
        if(n%2 != 0){
            sumZeroArray[arrIndex++] = 0;
        }
        for(;arrIndex<n; arrIndex+=2){
            sumZeroArray[arrIndex] = val;
            sumZeroArray[arrIndex+1] = -1*val;
            val++;
        }
        return sumZeroArray;
    }
}
