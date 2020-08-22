package Search;

import java.util.List;

/*
(This problem is an interactive problem.)

A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.

You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:

BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y) (0-indexed).
BinaryMatrix.dimensions() returns a list of 2 elements [m, n], which means the matrix is m * n.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes you're given the binary matrix mat as input in the following four examples. You will not have access the binary matrix directly.



Example 1:



Input: mat = [[0,0],[1,1]]
Output: 0
Example 2:



Input: mat = [[0,0],[0,1]]
Output: 1
Example 3:



Input: mat = [[0,0],[0,0]]
Output: -1
Example 4:



Input: mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
Output: 1


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 100
mat[i][j] is either 0 or 1.
mat[i] is sorted in a non-decreasing way.
 */
public class LeftMostColumnWithOne {

    /*
    Here start from right corner. if 1 keep moving left else move down
    Running time O(N+M)
    Space is O(1)
     */
    public int leftMostColumnWithOneOptimal(BinaryMatrix bm) {

        List<Integer> matSize = bm.dimensions();
        int row = 0;
        int col = matSize.get(1)-1;
        int lastVal = 0;
        while(row<matSize.get(0) && col>=0){
            lastVal = bm.get(row, col);
            if(lastVal == 1){
                col--;
            } else {
                row++;
            }
        }
        return (col+1<matSize.get(1)?col+1:-1);
    }

    /*
    Here do binary search in each row and keep shirinking the number of columns to search
    Running time O(M*log(N))
    Space is O(1)
     */
    public int leftMostColumnWithOne(BinaryMatrix bm) {

        List<Integer> matSize = bm.dimensions();
        boolean minIndexPresent = false;
        int minIndex = matSize.get(1) -1;
        for(int row =0; row<matSize.get(0); row++){
            int currIndex = indexWithFirstOne(row, minIndex, bm);
            if(currIndex != -1 && minIndex>=currIndex){
                minIndex = currIndex;
                minIndexPresent = true;
            }
        }

        return (minIndexPresent)?minIndex:-1;
    }

    int indexWithFirstOne(int row, int n, BinaryMatrix bm){
        int st = 0;
        int end = n;
        int minIndex = -1;
        while(st<=end){
            int mid = st + (end-st)/2;
            if(bm.get(row,mid) == 1){
                minIndex = mid;
                end = mid-1;
            } else {
                st = mid+1;
            }
        }
        return minIndex;
    }
/*
This is provided in the question
 */
    interface BinaryMatrix {
        public int get(int x, int y);

        public List<Integer> dimensions();
    }
}
