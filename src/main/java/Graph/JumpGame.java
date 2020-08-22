package Graph;

/*
https://leetcode.com/problems/jump-game-iii/
Given an array of non-negative integers arr, you are initially positioned at start index of the array.
When you are at index i, you can jump to i + arr[i] or i - arr[i],
check if you can reach to any index with value 0.

Notice that you can not jump outside of the array at any time.



Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation:
All possible ways to reach at index 3 with value 0 are:
index 5 -> index 4 -> index 1 -> index 3
index 5 -> index 6 -> index 4 -> index 1 -> index 3
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true
Explanation:
One possible way to reach at index 3 with value 0 is:
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.


Constraints:

1 <= arr.length <= 5 * 10^4
0 <= arr[i] < arr.length
0 <= start < arr.length
 */
/*
Solution: basically DFS with changing the input array to get info reg the visited
Running time is O(n)
The space needed in O(n) due to recursion
 */
public class JumpGame {
    public boolean canReach(int[] arr, int start) {
        if(arr[start]>0){
            return false;
        } else if(arr[start] == 0){
            return true;
        }
        boolean hasReached = false;
        int fwd = start + arr[start];
        int backwd = start - arr[start];
        arr[start] *= -1; // You can make it -1, if you dont want to restore the input array
        if(fwd < arr.length){
            hasReached |= canReach(arr, fwd);
        }
        if(backwd >=0){
            hasReached |= canReach(arr, backwd);
        }
        return hasReached;
    }
}
