package Backtracking;

/*
https://leetcode.com/problems/word-search/
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell,
where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 */
/*
Running time is O(m*n*4^l) where m is the rows, n columns and l is the length of the string
Space needed is O(m*n)
 */
public class WordSearch {
    int[][] directions = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0 || word == null || word.length()==0){
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int row = 0; row<board.length; row++){
            for(int col = 0; col<board[0].length; col++){
                if(existUtil(board, word, 0, row, col, visited)){
                    return true;
                }
            }
        }
        return false;
    }

    boolean existUtil(char[][] board, String word, int wIndex, int row, int col, boolean[][] visited){
        if(row<0 || row >= board.length || col<0 || col>=board[0].length || wIndex>=word.length() || visited[row][col] || board[row][col]!=word.charAt(wIndex)){
            return false;
        }
        if(wIndex == word.length()-1){
            return true;
        }
        visited[row][col] = true;
        boolean wordExists = false;
        for(int[] dir : directions){
            wordExists |= existUtil(board, word, wIndex+1, row + dir[0], col + dir[1], visited);
            if(wordExists){
                break;
            }
        }
        visited[row][col] = false;
        return wordExists;
    }}
