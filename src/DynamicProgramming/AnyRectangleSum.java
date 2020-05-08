package DynamicProgramming;

/*
https://leetcode.com/problems/range-sum-query-2d-immutable/
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
/*
Running time constructor = O(n*m)
Running time sumRegion = O(1)
Space needed is O(1) as we are re-using the input matrix
 */
public class AnyRectangleSum {
    int[][] mat;
    public AnyRectangleSum(int[][] matrix) {
        for(int r = 0; r<matrix.length; r++){
            for(int c = 0; c<matrix[0].length; c++){
                if(c-1 >= 0){
                    matrix[r][c] += matrix[r][c-1];
                }
                if(r-1>= 0){
                    matrix[r][c] += matrix[r-1][c];
                }
                if(c-1>=0 && r-1>=0){
                    matrix[r][c] -= matrix[r-1][c-1];
                }
            }
        }
        mat = matrix;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sumRegion = 0;
        if(row1>=0 && row2<mat.length && col1>=0 && col2 <mat[0].length){
            sumRegion += mat[row2][col2];
            if(row1-1>=0){
                sumRegion -= mat[row1-1][col2];
            }
            if(col1-1>=0){
                sumRegion -= mat[row2][col1-1];
            }
            if(row1-1>=0 && col1-1>=0){
                sumRegion += mat[row1-1][col1-1];
            }
        }
        return sumRegion;
    }
}
