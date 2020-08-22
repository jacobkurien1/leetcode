package matrix;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/sparse-matrix-multiplication/
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

Input:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

Output:

     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
 */
public class MultiplySparseMatrix {
    /*
    Optimal approach: Preprocess the matrices
    Worst case running time is O(n*m*k)
    Space needed is O(n*m + m*k)
    This should perform much better in an avg case
     */
    public int[][] multiplyOptimal(int[][] A, int[][] B) {
        int outR = A.length;
        int outC = B[0].length;
        int[][] out = new int[outR][outC];

        List<List<Integer>> aList = getNonZeroIndices(A);
        List<List<Integer>> bList = getNonZeroIndicesTranspose(B);
        for(int r = 0; r<outR; r++){
            for(int c = 0;c<outC; c++){
                out[r][c] = computeVal(aList.get(r), bList.get(c), A, B, r, c);
            }
        }
        return out;
    }

    int computeVal(List<Integer> aIndex, List<Integer> bIndex, int[][] A, int[][] B, int r, int c){
        int val = 0;
        int aCurr = 0;
        int bCurr =0;
        while(aCurr <aIndex.size() && bCurr <bIndex.size()){
            if(aIndex.get(aCurr) == bIndex.get(bCurr)){
                val += A[r][aIndex.get(aCurr)] * B[aIndex.get(aCurr)][c];
                aCurr++;
                bCurr++;
            } else if (aIndex.get(aCurr)<bIndex.get(bCurr)){
                aCurr++;
            } else {
                bCurr++;
            }
        }
        return val;
    }

    List<List<Integer>>  getNonZeroIndices(int[][] mat){
        List<List<Integer>> ret = new ArrayList<>();
        for(int r = 0; r<mat.length; r++){
            ret.add(new ArrayList<>());
            for(int c = 0; c<mat[0].length; c++){
                if(mat[r][c] != 0){
                    ret.get(ret.size()-1).add(c);
                }
            }
        }
        return ret;
    }

    List<List<Integer>>  getNonZeroIndicesTranspose(int[][] mat){
        List<List<Integer>> ret = new ArrayList<>();
        for(int c = 0; c<mat[0].length; c++){
            ret.add(new ArrayList<>());
            for(int r = 0; r<mat.length; r++){
                if(mat[r][c] != 0){
                    ret.get(ret.size()-1).add(r);
                }
            }
        }
        return ret;
    }

/*---------------------------------------------------------------------------------*/
    /*
    NOn optimal but worst case is O(n*m*k)
    Space needed is O(1) if we dont account the output matrix
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int outR = A.length;
        int outC = B[0].length;
        int[][] out = new int[outR][outC];
        for(int r = 0; r<outR; r++){
            for(int c = 0;c<outC; c++){
                for(int k = 0; k<B.length; k++){
                    if(A[r][k]!= 0 && B[k][c]!=0){
                        out[r][c] += A[r][k]*B[k][c];
                    }
                }

            }
        }
        return out;
    }
}
