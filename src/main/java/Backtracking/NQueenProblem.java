package main.java.Backtracking;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/n-queens/
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
/*
Running time is O(n!) as we can place first queen in n places, next queen goes to n-1 places and so on
Space needed is O(n) to track the attacking positions of the queen
 */
public class NQueenProblem {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        // initialization
        for(int r= 0; r<n; r++){
            for(int c = 0; c<n; c++){
                board[r][c] = '.';
            }
        }
        boolean[] colWithQueen = new boolean[n];
        boolean[] diagWithQueen = new boolean[2*n];
        boolean[] antiDiagWithQueen = new boolean[2*n];
        solveNQueensRecur(board, 0, colWithQueen, diagWithQueen, antiDiagWithQueen);
        return solvedBoards;
    }

    List<List<String>> solvedBoards = new ArrayList<>();

    void solveNQueensRecur(char[][] board, int r, boolean[] colWithQueen, boolean[] diagWithQueen, boolean[] antiDiagWithQueen){
        if(r>=board.length){
            return;
        }
        List<String> solvedBoard = null;
        for(int c = 0; c<board[0].length; c++){
            if(colWithQueen[c] || diagWithQueen[r-c+board.length] || antiDiagWithQueen[r+c]){
                continue;
            }

            board[r][c] = 'Q';
            colWithQueen[c] = true;
            diagWithQueen[r-c+board.length] = true;
            antiDiagWithQueen[r+c] = true;

            if(r == board.length-1){
                //reached the last row
                solvedBoards.add(getBoard(board));
            } else {
                solveNQueensRecur(board, r+1, colWithQueen, diagWithQueen, antiDiagWithQueen);
            }

            board[r][c] = '.';
            colWithQueen[c] = false;
            diagWithQueen[r-c+board.length] = false;
            antiDiagWithQueen[r+c] = false;
        }
    }

    List<String> getBoard(char[][] board){
        List<String> ret = new ArrayList<>();
        for(int r= 0; r<board.length; r++){
            StringBuilder sb = new StringBuilder();
            for(int c=0; c<board[0].length; c++){
                sb.append(Character.toString(board[r][c]));
            }
            ret.add(sb.toString());
            sb = new StringBuilder();
        }
        return ret;
    }
}
