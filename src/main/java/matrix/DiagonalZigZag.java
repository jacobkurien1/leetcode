package matrix;

/*
https://leetcode.com/problems/diagonal-traverse/
Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.



Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]

Explanation:



Note:

The total number of elements of the given matrix will not exceed 10,000.
 */
/*
Running time is O(m*n) where m is the total rows and  n is the total columns
Space needed is O(m*n) to store the output
 */
public class DiagonalZigZag {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length == 0){
            return new int[0];
        }
        int totalRows = matrix.length;
        int totalCol = matrix[0].length;
        int[] diagonalOrder = new int[totalRows* totalCol];
        int diagOrderIndex =0;
        int r =0;
        int c =0;
        boolean goUp = true;

        while(r < totalRows && c < totalCol){
            diagonalOrder[diagOrderIndex++] = matrix[r][c];
            PointAndOrientation pAndOrientation = nextPointAndOrientation(r,c,goUp, totalRows, totalCol);
            r = pAndOrientation.row;
            c = pAndOrientation.col;
            goUp = pAndOrientation.goUp;
        }
        return diagonalOrder;
    }

    PointAndOrientation nextPointAndOrientation(int r, int c, boolean goUp, int totalRows, int totalCol) {
        if(goUp){
            r--;
            c++;
            if(c>=totalCol){
                r+=2;
                c--;
                goUp = false;
            } else if(r<0){
                r++;
                goUp = false;
            }
        } else {
            r++;
            c--;
            if(r>=totalRows){
                c+=2;
                r--;
                goUp = true;
            } else if(c<0){
                c++;
                goUp = true;
            }
        }
        return new PointAndOrientation(r,c, goUp);
    }

    class PointAndOrientation {
        int row;
        int col;
        boolean goUp;
        PointAndOrientation (int row, int col, boolean goUp){
            this.row = row;
            this.col = col;
            this.goUp = goUp;
        }
    }
}
