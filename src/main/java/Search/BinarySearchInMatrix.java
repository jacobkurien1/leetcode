package main.java.Search;

/*
https://leetcode.com/problems/search-a-2d-matrix/
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
 */
/*
Running time is O(log(n*m))
Space needed is O(1)
 */
public class BinarySearchInMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0){
            return false;
        }
        int st =0;
        int end = matrix.length * matrix[0].length -1;
        int totalCol = matrix[0].length;
        while(st<=end){
            int mid = st+(end-st)/2;
            Cell c = getCell(mid, totalCol);
            if(matrix[c.row][c.col]==target){
                return true;
            } else if(matrix[c.row][c.col] > target){
                end = mid-1;
            } else {
                st = mid+1;
            }
        }
        return false;
    }

    Cell getCell(int offset, int totalCol){
        return new Cell(offset/totalCol, offset%totalCol);
    }

    class Cell{
        int row;
        int col;
        public Cell(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}
