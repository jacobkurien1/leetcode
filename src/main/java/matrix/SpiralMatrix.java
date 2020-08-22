package matrix;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/spiral-matrix/
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<Integer>();
        if(matrix.length == 0){
            return ret;
        }
        int top = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length -1;
        while(top<=bottom && left<=right){
            for(int col = left; col<=right; col++){
                ret.add(matrix[top][col]);
            }
            top++;
            for(int row = top; row<=bottom; row++){
                ret.add(matrix[row][right]);
            }
            right--;
            if(top<=bottom){
                for(int col = right; col>=left; col--){
                    ret.add(matrix[bottom][col]);
                }
                bottom--;
            }
            if(left<=right){
                for (int row = bottom; row>=top; row--){
                    ret.add(matrix[row][left]);
                }
                left++;
            }
        }
        return ret;
    }
}
