package stack;

import java.util.Stack;
/*
https://leetcode.com/problems/maximal-rectangle/
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.



Example 1:

Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.

Example 2:

Input: matrix = []
Output: 0

Example 3:

Input: matrix = [["0"]]
Output: 0

Example 4:

Input: matrix = [["1"]]
Output: 1

Example 5:

Input: matrix = [["0","0"]]
Output: 0



Constraints:

    rows == matrix.length
    cols == matrix[i].length
    0 <= row, cols <= 200
    matrix[i][j] is '0' or '1'.


 */
/*
Here we will reduce this problem to be histogram per row and then use the max histogram for 1D array.
Running time is O(n*m)
Space needed is O(m)
 */
public class LargestRectangleInMatrix {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0){
            return 0;
        }
        int[] rowCumm = new int[matrix[0].length];
        int maxArea = 0;
        for(int r = 0; r< matrix.length; r++){
            for(int c = 0;c<matrix[0].length; c++){
                if(matrix[r][c] == '0'){
                    rowCumm[c] = 0;
                } else{
                    rowCumm[c] += 1;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleHistogram(rowCumm));
        }
        return maxArea;
    }

    public int largestRectangleHistogram(int[] heights){
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int maxArea = 0;
        for(int i =0;i<heights.length; i++){
            while(st.peek() != -1 && heights[st.peek()]>heights[i]){
                maxArea = Math.max(maxArea, heights[st.pop()]*(i-st.peek()-1));
            }
            st.push(i);
        }
        while(st.peek()!=-1){
            maxArea = Math.max(maxArea, heights[st.pop()]*(heights.length-st.peek()-1));
        }
        return maxArea;
    }
}
