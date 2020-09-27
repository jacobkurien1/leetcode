package main.java.ArraysProblem;

/*
https://leetcode.com/problems/design-tic-tac-toe/
Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
Follow up:
Could you do better than O(n2) per move() operation?
 */
/*
Running time is O(1)
Space needed is O(n)
 */
public class TicTacToe {
    int[] rowSum;
    int[] colSum;
    int diag = 0;
    int antiDiag = 0;
    int n;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rowSum = new int[n];
        colSum = new int[n];
        this.n = n;
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int playerVal = getPlayerVal(player);
        rowSum[row] += playerVal;
        if(hasWon(rowSum[row])){
            return getPlayer(rowSum[row]);
        }
        colSum[col] += playerVal;
        if(hasWon(colSum[col])){
            return getPlayer(colSum[col]);
        }
        if (row == col){
            //diagnal
            diag+=playerVal;
            if(hasWon(diag)){
                return getPlayer(diag);
            }
        }
        if(row + col == n-1){
            //anti-diag
            antiDiag += playerVal;
            if(hasWon(antiDiag)){
                return getPlayer(antiDiag);
            }
        }
        return 0;
    }

    boolean hasWon(int val){
        return Math.abs(val) == n;
    }

    int getPlayerVal(int player){
        return (player == 2)?-1:1;
    }

    int getPlayer(int playerVal){
        return (playerVal<0)?2:1;
    }


}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */