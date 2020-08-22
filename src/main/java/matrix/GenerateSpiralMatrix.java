package matrix;

/*
https://leetcode.com/problems/spiral-matrix-ii/
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 */
public class GenerateSpiralMatrix {
    public int[][] generateMatrix(int n) {
        int left = 0;
        int right = n-1;
        int top = 0;
        int bottom = n-1;
        int val = 1;
        int[][] mat = new int[n][n];
        while(top <=n/2){
            //first row
            for(int col =left; col<=right; col++){
                mat[top][col] = val++;
            }
            top++;
            for(int row = top; row<=bottom; row++){
                mat[row][right] = val++;
            }
            right--;
            for(int col =right; col>=left; col--){
                mat[bottom][col] = val++;
            }
            bottom--;
            for(int row = bottom; row>=top; row--){
                mat[row][left] = val++;
            }
            left++;
        }
        return mat;
    }
}
