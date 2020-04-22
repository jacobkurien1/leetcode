package Trie;

import java.util.HashMap;

/*
https://leetcode.com/problems/add-and-search-word-data-structure-design/
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
 */
public class AddAndSearchWord {
    TrieNode root = null;
    /** Initialize your data structure here. */
    public AddAndSearchWord() {
        root = new TrieNode((char)0);
    }

    /** Adds a word into the data structure. */
    //Running time O(wod.length())
    public void addWord(String word) {
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode(c));
            }
            curr = curr.children.get(c);
        }
        curr.isLast = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     . a t
     0 1 2

     0
     |  \
     b   k
     |
     a
     |
     t
     */
    // Running time is 26^0 + 26^1+26^2 + 26^3 + 26^h = 26^(h+1) -1
    public boolean search(String word) {
        return searchUtil(word, 0, root);
    }

    private boolean searchUtil(String word, int wIndex, TrieNode curr){
        if(curr != null && wIndex == word.length()){
            return curr.isLast;
        }
        if(curr == null || word == null || wIndex>=word.length()){
            return false;
        }

        char currChar = word.charAt(wIndex);
        if(currChar != '.'){
            TrieNode n = curr.children.getOrDefault(word.charAt(wIndex), null);
            return searchUtil(word, wIndex+1, n);
        } else {
            for(TrieNode n: curr.children.values()){
                if(searchUtil(word, wIndex+1, n)){
                    return true;
                }
            }
        }
        return false;
    }


    class TrieNode {
        char val;
        boolean isLast = false;
        HashMap<Character, TrieNode> children;
        TrieNode (char val){
            this.val = val;
            children = new HashMap<>();
        }
    }
}
