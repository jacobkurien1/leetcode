package main.java.Backtracking;

/*
https://leetcode.com/problems/sudoku-solver/
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.



Example 1:


Input: board =
[["5","3",".",".","7",".",".",".","."],
["6",".",".","1","9","5",".",".","."],
[".","9","8",".",".",".",".","6","."],
["8",".",".",".","6",".",".",".","3"],
["4",".",".","8",".","3",".",".","1"],
["7",".",".",".","2",".",".",".","6"],
[".","6",".",".",".",".","2","8","."],
[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output:
[["5","3","4","6","7","8","9","1","2"],
["6","7","2","1","9","5","3","4","8"],
["1","9","8","3","4","2","5","6","7"],
["8","5","9","7","6","1","4","2","3"],
["4","2","6","8","5","3","7","9","1"],
["7","1","3","9","2","4","8","5","6"],
["9","6","1","5","3","7","2","8","4"],
["2","8","7","4","1","9","6","3","5"],
["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:




Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
 */
/*
Running time is O(9^(n*m))
Space needed is O(n*m)
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] boxes = new boolean[9][10];
        for(int r = 0; r<board.length; r++){
            for(int c=0; c<board[0].length; c++){
                char boardVal = board[r][c];
                if(boardVal != '.'){
                    rows[r][boardVal-'0'] = true;
                    cols[c][boardVal - '0'] = true;
                    boxes[r/3*3+c/3][boardVal - '0'] = true;
                }
            }
        }
        solveSudokuRecur(board, rows, cols, boxes);
    }

    boolean solveSudokuRecur(char[][] board,
                             boolean[][] rows,
                             boolean[][] cols,
                             boolean[][] boxes){
        for(int r=0; r<board.length; r++){
            for(int c=0;c<board[0].length; c++){
                if(board[r][c] != '.') continue;

                for(int i = 1; i<=9; i++){
                    if(!canPutValue(r, c, i, rows, cols, boxes)){
                        continue;
                    }
                    board[r][c] = (char)(i + '0');
                    rows[r][i] = true;
                    cols[c][i] = true;
                    boxes[r/3*3+c/3][i] = true;

                    if(solveSudokuRecur(board, rows, cols, boxes)){
                        return true;
                    }

                    //backtrack
                    board[r][c] = '.';
                    rows[r][i] = false;
                    cols[c][i] = false;
                    boxes[r/3*3+c/3][i] = false;
                }
                return false;
            }
        }
        return true;
    }

    boolean canPutValue(int r, int c, int val,
                        boolean[][] rows,
                        boolean[][] cols,
                        boolean[][] boxes){
        return !rows[r][val] && !cols[c][val] && !boxes[r/3*3+c/3][val];
    }

    class Cell{
        int r;
        int c;
        Cell(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}
