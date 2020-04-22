package Trie;

import java.util.*;

/*
https://leetcode.com/problems/search-suggestions-system/
Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product
names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord.
If there are more than three products with a common prefix return the three lexicographically minimums products.

Return list of lists of the suggested products after each character of searchWord is typed.



Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [
["mobile","moneypot","monitor"],
["mobile","moneypot","monitor"],
["mouse","mousepad"],
["mouse","mousepad"],
["mouse","mousepad"]
]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Example 3:

Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
Example 4:

Input: products = ["havana"], searchWord = "tatiana"
Output: [[],[],[],[],[],[],[]]


Constraints:

1 <= products.length <= 1000
There are no repeated elements in products.
1 <= Σ products[i].length <= 2 * 10^4
All characters of products[i] are lower-case English letters.
1 <= searchWord.length <= 1000
All characters of searchWord are lower-case English letters.
 */
public class SearchSuggestionsSystem {
    /*
    Running time is (Searchword.length)
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = new TrieNode((char)0);
        for(String product : products){
            add(root, product);
        }
        List<List<String>> suggestionList = new ArrayList<>();
        TrieNode curr = root;
        for(char c: searchWord.toCharArray()){
            List<String> suggestions = new ArrayList<>();
            if(curr != null && curr.children.containsKey(c)){
                PriorityQueue<String> pq = curr.children.get(c).pq;
                while(!pq.isEmpty()){
                    suggestions.add(pq.poll());
                }
                curr = curr.children.get(c);
            } else{
                curr = null;
            }
            Collections.reverse(suggestions); // to get it in ascending order
            suggestionList.add(suggestions);
        }
        return suggestionList;
    }

    /*
    Running time is O(str.length())
    Note the addition to pq is constant since the size is always 3
     */
    void add(TrieNode root, String str){
        TrieNode curr = root;
        for(char c: str.toCharArray()){
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode(c));
            }
            curr = curr.children.get(c);
            curr.pq.add(str);
            if(curr.pq.size()>3){
                curr.pq.poll();
            }
        }
    }

    class TrieNode {
        char val;
        HashMap<Character, TrieNode> children;
        PriorityQueue<String> pq; // used as a maxheap

        public TrieNode(char val) {
            this.val = val;
            children = new HashMap<>();
            pq = new PriorityQueue<>((a, b) -> b.compareTo(a));
        }
    }
}
