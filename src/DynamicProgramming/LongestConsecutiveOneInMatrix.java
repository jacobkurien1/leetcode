package DynamicProgramming;
/*
https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/
Given a 01 matrix M, find the longest line of consecutive one in the matrix.
The line could be horizontal, vertical, diagonal or anti-diagonal.
Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3
Hint: The number of elements in the given matrix will not exceed 10,000.
 */
/*
Running time is O(n*m)
Space needed is O(m)
 */
public class LongestConsecutiveOneInMatrix {
    public int longestLine(int[][] mat) {
        if(mat.length == 0){
            return 0;
        }
        int columnLen = mat[0].length;
        int[] diag = new int[columnLen];
        int[] top = new int[columnLen];
        int[] antiDiag = new int[columnLen];
        int longestLineLen = 0;
        for(int r = 0; r<mat.length; r++){
            int[] currDiag = new int[columnLen];
            int[] currTop = new int[columnLen];
            int currLeft = 0; // just the previous value is needed for left
            int[] currAntiDiag = new int[columnLen];
            for(int c = 0; c<columnLen; c++){
                if(mat[r][c] == 1){
                    currDiag[c] = (c-1<0?0:diag[c-1]) +1;
                    currTop[c] = top[c] +1;
                    currLeft += 1; // same left array used
                    currAntiDiag[c] = (c+1<columnLen?antiDiag[c+1]:0) +1;
                    longestLineLen = Math.max(longestLineLen, currDiag[c]);
                    longestLineLen = Math.max(longestLineLen, currTop[c]);
                    longestLineLen = Math.max(longestLineLen, currLeft);
                    longestLineLen = Math.max(longestLineLen, currAntiDiag[c]);

                } else {
                    currLeft =0;
                }
            }
            diag = currDiag;
            top = currTop;
            antiDiag = currAntiDiag;
        }
        return longestLineLen;
    }
}
