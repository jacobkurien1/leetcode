package Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

/*
https://leetcode.com/problems/word-search-ii/
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent"
cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



Example:

Input:
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]


Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.
 */
/*
Running time is O(n*m* 4* 3^(l-1)) Initially we have 4 directions to go and then we have 3 directions to go.
l is the max length of the word in the dictionary
Space is O(total number of letters in the dictionary)
 */
public class WordSearchForAllDictionaryWords {
    private int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> allWords = new HashSet<>();
        if(board == null || board.length == 0){
            return new ArrayList<String>();
        }
        Trie trie = new Trie();
        for(String str : words){
            trie.add(str);
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int row = 0; row<board.length;row++){
            for(int col = 0; col<board[0].length; col++){
                DFS(board, visited, trie.root, row, col, new StringBuilder(), allWords);
            }
        }

        return new ArrayList<>(allWords);
    }

    void DFS(char[][] board, boolean[][] visited, TrieNode n, int row, int col,
             StringBuilder sb, Set<String> allWords){
        if(row<0 || row>=board.length || col<0 || col>=board[0].length ||
                visited[row][col] || n == null || !n.neighbours.containsKey(board[row][col])){
            return;
        }
        sb.append(board[row][col]);
        visited[row][col] = true;
        if(n.neighbours.get(board[row][col]).isEnd){
            allWords.add(sb.toString());
        }
        for(int[] dir : directions){
            DFS(board, visited, n.neighbours.get(board[row][col]), row+dir[0], col+dir[1], sb, allWords);
        }
        visited[row][col] = false;
        sb.deleteCharAt(sb.length()-1);
    }

    class Trie{
        TrieNode root;
        public Trie(){
            root = new TrieNode(' ');
        }

        public void add(String str){
            TrieNode curr = root;
            for(int i = 0; i<str.length(); i++){
                if(!curr.neighbours.containsKey(str.charAt(i))){
                    curr.neighbours.put(str.charAt(i), new TrieNode(str.charAt(i)));
                }
                curr = curr.neighbours.get(str.charAt(i));
            }
            curr.isEnd = true;
        }
    }

    class TrieNode{
        char c;
        boolean isEnd;
        Map<Character, TrieNode> neighbours;
        public TrieNode(char c){
            this.c = c;
            neighbours = new HashMap<>();
            isEnd = false;
        }
    }
}
