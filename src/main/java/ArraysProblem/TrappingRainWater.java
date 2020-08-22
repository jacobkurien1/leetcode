package ArraysProblem;
/*
https://leetcode.com/problems/trapping-rain-water/
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.
Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */
/*
Solution: For each height, we need to calculate the max on the left side and max on the right side and take the min.
The water trapped for that index is min(leftMax, rightMax) - height[i]

Running time is O(n)
Space is O(1)
 */
public class TrappingRainWater {
    public int trap(int[] heights) {
        int leftIndex =0;
        int rightIndex = heights.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int trappedWater = 0;
        while(leftIndex < rightIndex) {
            leftMax = Math.max(leftMax, heights[leftIndex]);
            rightMax = Math.max(rightMax, heights[rightIndex]);
            if(leftMax <= rightMax){
                trappedWater += (leftMax - heights[leftIndex++]);
            } else {
                trappedWater += (rightMax - heights[rightIndex--]);
            }
        }
        return trappedWater;
    }
}
