package DynamicProgramming;
/*
https://leetcode.com/problems/count-square-submatrices-with-all-ones/
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.



Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation:
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
Example 2:

Input: matrix =
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation:
There are 6 squares of side 1.
There is 1 square of side 2.
Total number of squares = 6 + 1 = 7.


Constraints:

1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1
 */
/*
Running time O(n*m)
Space needed is O(1)
 */
public class SquareSubMatrixCount {
    public int countSquares(int[][] matrix) {
        int sqCount = 0;
        for(int r = 0; r<matrix.length;r++){
            for(int c = 0; c<matrix[0].length; c++){
                if(matrix[r][c] != 0){
                    int min = (c-1>=0?matrix[r][c-1]:0);
                    min = Math.min(min, (r-1>=0?matrix[r-1][c]:0));
                    min= Math.min(min, ((r-1>=0 && c-1>=0)?matrix[r-1][c-1]:0));
                    matrix[r][c] = min + 1;
                    sqCount += min + 1;
                }
            }
        }
        return sqCount;
    }
}
