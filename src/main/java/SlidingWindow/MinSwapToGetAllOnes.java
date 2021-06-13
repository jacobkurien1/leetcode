package main.java.SlidingWindow;
/*
https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/
1151. Minimum Swaps to Group All 1's Together
Medium

458

4

Add to List

Share
Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.



Example 1:

Input: data = [1,0,1,0,1]
Output: 1
Explanation:
There are 3 ways to group all 1's together:
[1,1,1,0,0] using 1 swap.
[0,1,1,1,0] using 2 swaps.
[0,0,1,1,1] using 1 swap.
The minimum is 1.
Example 2:

Input: data = [0,0,0,1,0]
Output: 0
Explanation:
Since there is only one 1 in the array, no swaps needed.
Example 3:

Input: data = [1,0,1,0,1,0,0,1,1,0,1]
Output: 3
Explanation:
One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
Example 4:

Input: data = [1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1]
Output: 8


Constraints:

1 <= data.length <= 105
data[i] is 0 or 1.
 */
/*
Running time O(n)
Space O(1)
 */
public class MinSwapToGetAllOnes {
    public int minSwaps(int[] data) {
        int ones = 0;
        for(int d: data){
            if(d == 1){
                ++ones;
            }
        }
        int zeros = 0;
        int st = 0;
        int end = 0;
        for(int i = 0; i<ones;i++){
            if(data[end] == 0){
                ++zeros;
            }
            end++;
        }
        int minZeros = zeros;
        while(end<data.length){
            if(data[end++] == 0){
                ++zeros;
            }
            if(data[st++] == 0){
                --zeros;
            }
            minZeros = Math.min(minZeros, zeros);
        }
        return minZeros;
    }
}
