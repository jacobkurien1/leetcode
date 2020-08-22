package Graph;

import java.util.*;
/*
https://leetcode.com/problems/longest-string-chain/
Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.



Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".


Note:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.
 */
/*
	The running time is O(nlogn + n*(s)) where s is the length of the string in the array.
	n is the number of strings and it takes logn to add elements to the treeMap.
	n*s because we get all the words and remove 1 character at a time.
	Note the DFS run time is O(V+E) here E can be only n-1, so it will not affect the running time.
	Also a thing to note, I am doing DFS in the order of words with length() in descending order, and the visited set helps me do the memoization.
 */
public class LongestStringChain {
    public int longestStrChain(String[] words) {
        if(words.length == 0){
            return 0;
        }
        Map<Integer, Set<String>> sizeMap = new TreeMap<>((a, b)-> Integer.compare(b,a));
        Graph g = new Graph();
        for(String word : words){
            Set<String> set = sizeMap.getOrDefault(word.length(), new HashSet<String>());
            set.add(word);
            sizeMap.put(word.length(), set);
        }
        List<Set<String>> allMapValues = new ArrayList<>(sizeMap.values());
        for(int i = 0; i<allMapValues.size()-1; i++){
            //add the edges
            Set<String> nextSet = allMapValues.get(i+1);
            for(String str : allMapValues.get(i)){
                for(int strIndex = 0; strIndex<str.length(); strIndex++){
                    String newString = (strIndex == 0)? str.substring(strIndex+1): str.substring(0, strIndex) + str.substring(strIndex+1);
                    if(nextSet.contains(newString)){
                        g.addEdge(str, newString);
                    }
                }
            }
        }

        Set<String> visited = new HashSet<String>();
        int maxLen = 0;
        for(int i = 0; i<allMapValues.size(); i++){
            for(String str:allMapValues.get(i)){
                if(!visited.contains(str)){
                    maxLen = Math.max(maxLen, g.getMaxLength(str, visited));
                }
            }
        }
        return (maxLen == 0)? 1 : maxLen;
    }

    class Graph{
        HashMap<String, Vertex> vertices = new HashMap<>();

        void addEdge(String source, String target){
            if(!vertices.containsKey(source)){
                vertices.put(source, new Vertex(source));
            }
            if(!vertices.containsKey(target)){
                vertices.put(target, new Vertex(target));
            }
            vertices.get(source).neighbours.add(vertices.get(target));
        }

        int getMaxLength(String str, Set<String> visited){
            return dfsUtil(vertices.get(str), visited);
        }

        int dfsUtil(Vertex v, Set<String> visited){
            if(v == null){
                return 0;
            }
            visited.add(v.val);
            int maxLen = 0;
            for(Vertex n : v.neighbours){
                if(!visited.contains(n.val)){
                    maxLen = Math.max(maxLen, dfsUtil(n, visited));
                }
            }
            return maxLen+1;
        }
    }

    class Vertex{
        String val;
        List<Vertex> neighbours;
        Vertex(String val){
            this.val = val;
            neighbours = new ArrayList<Vertex>();
        }
    }
}
