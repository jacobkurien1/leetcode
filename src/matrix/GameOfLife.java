package matrix;

/*
https://leetcode.com/problems/game-of-life/
According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.
The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:

Input:
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output:
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
Follow up:

Could you solve it in-place? Remember that the board needs to be updated at the same time:
You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite,
which would cause problems when the active area encroaches the border of the array.
How would you address these problems?
 */
/*
Running time is O(n*m)
Space is O(1)
 */
public class GameOfLife {
    //0-dead 1-alive
    //-1-aliveInNext 2->deadInNext
    /*
    [
      [0,2,0],
      [0,-1,1],
      [1,1,1],
      [0,0,0]
    ]
    */
    public void gameOfLife(int[][] board) {
        for(int row = 0; row<board.length; row++){
            for(int col = 0; col<board[0].length; col++){
                int liveNeighbours = getLiveNeighbours(board, row, col);
                if(board[row][col] >0){
                    //live
                    if(liveNeighbours !=2 && liveNeighbours != 3){
                        board[row][col] = 2;
                    }
                } else {
                    if(liveNeighbours == 3){
                        board[row][col]  = -1;
                    }
                }
            }
        }

        for(int row = 0; row<board.length; row++){
            for(int col = 0; col<board[0].length; col++){
                if(Math.abs(board[row][col]) ==1 ){
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }
    int[][] directions = new int[][]{
            {1,0},
            {-1,0},
            {0,1},
            {0,-1},
            {1,1},
            {1, -1},
            {-1,1},
            {-1,-1}
    };
    int getLiveNeighbours(int[][] board, int row, int col){
        int live = 0;
        for(int[] direction:directions){
            int newR = row + direction[0];
            int newC = col+direction[1];
            if(newR<0 || newR>=board.length || newC <0 || newC>=board[0].length){
                continue;
            }
            if(board[newR][newC]>0){
                live++;
            }
        }
        return live;
    }
}
