package stack;

import java.util.Stack;

/*
https://leetcode.com/problems/largest-rectangle-in-histogram/
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.



Example 1:

Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

Example 2:

Input: heights = [2,4]
Output: 4



Constraints:

    1 <= heights.length <= 105
    0 <= heights[i] <= 104


 */
/*
Each time you pop, you are getting the index at which the height is less than the current
index at left and right.
Time complexity is O(n)
Space complexity is O(n)
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int maxArea = 0;
        for(int i = 0; i<heights.length; i++){
            while(st.peek() != -1 && heights[st.peek()]>heights[i]){
                maxArea = Math.max(maxArea, heights[st.pop()] * (i-st.peek()-1));
            }
            st.push(i);
        }
        while(st.peek() != -1){
            maxArea = Math.max(maxArea, heights[st.pop()] *(heights.length - st.peek()-1));
        }
        return maxArea;
    }
}
