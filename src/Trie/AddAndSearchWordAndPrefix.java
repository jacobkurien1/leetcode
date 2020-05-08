package Trie;

import java.util.HashMap;

/*
https://leetcode.com/problems/implement-trie-prefix-tree/
Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
 */
/*
All the operations take O(k) where k is the length of the word
Space needed is O(n*k) where k is the length of the word and n is the number of words
 */
public class AddAndSearchWordAndPrefix {
    TrieNode root = null;
    /** Initialize your data structure here. */
    public AddAndSearchWordAndPrefix() {
        root = new TrieNode((char)0);
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for(int i = 0; i<word.length(); i++){
            if(!curr.hm.containsKey(word.charAt(i))){
                curr.hm.put(word.charAt(i), new TrieNode(word.charAt(i)));
            }
            curr = curr.hm.get(word.charAt(i));
        }
        curr.isLast = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return match(word, true);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return match(prefix, false);
    }

    boolean match(String word, boolean fullMatch){
        TrieNode curr = root;
        for(int i = 0; i<word.length();i++){
            char c = word.charAt(i);
            if(!curr.hm.containsKey(c)){
                return false;
            }
            curr = curr.hm.get(c);
        }
        return (fullMatch?curr.isLast:true);
    }

    class TrieNode {
        char c;
        boolean isLast = false;
        HashMap<Character, TrieNode> hm;
        TrieNode(char c){
            this.c = c;
            hm = new HashMap<>();
        }
    }
}
