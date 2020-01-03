package Graph;

import java.util.*;

/*
https://leetcode.com/problems/alien-dictionary/
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
]

Output: ""

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 */
/*
Solution: All all the nodes in the map and then add the edges based on word[i] and word[i+1]
Do topological sort to get the possible order of the Dictionary.

The running time O(V+E + (numOfWords*maxLengthOfWord)) where V is all the character of the dictionary and E is numOfWords-1
 */
public class LetterOrderFromDictionaryWords {
    public String alienOrder(String[] words) {
        HashMap<Character, Node> map = new HashMap<Character, Node>();
        for(int j = 0; j<words.length; j++){
            String first = words[j];
            String next = (j+1!=words.length)?words[j+1]:"";
            // Add all the characters of both the words;
            for(int i = 0; i<first.length(); i++){
                if(!map.containsKey(first.charAt(i))){
                    map.put(first.charAt(i), new Node(first.charAt(i)));
                }
            }
            for(int i = 0; i<next.length(); i++){
                if(!map.containsKey(next.charAt(i))){
                    map.put(next.charAt(i), new Node(next.charAt(i)));
                }
            }

            // Find the edge(based on) for the nodes
            for(int i = 0; i<Math.min(first.length(), next.length()); i++){
                if(first.charAt(i) != next.charAt(i)){
                    Node st = map.get(first.charAt(i));
                    Node end = map.get(next.charAt(i));
                    st.neighbours.add(end);
                    break;
                }
            }
        }
        //topological sort (Kahn's Algorithm as we are not sure that the graph is a DAG)
        // 1. Populate in-degree = number of incoming edges
        for(Node n : map.values()){
            for(Node neighbour : n.neighbours){
                neighbour.inDegree += 1;
            }
        }
        // 2. Add all nodes with indegree =0 to this queue
        Queue<Node> q = new LinkedList<Node>();
        for(Node n: map.values()){
            if(n.inDegree == 0){
                q.add(n);
            }
        }
        StringBuilder topologicalSortedOrder = new StringBuilder();
        int nodesOrdered = 0;
        while(!q.isEmpty()){
            Node n  = q.poll();
            topologicalSortedOrder.append(n.c);
            for(Node neighbour : n.neighbours){
                neighbour.inDegree -=1;
                if(neighbour.inDegree == 0){
                    q.add(neighbour);
                }
            }
            nodesOrdered++;
        }
        if(nodesOrdered != map.size()){
            return ""; // case when there is a cycle in the graph
        }
        return topologicalSortedOrder.toString();
    }

    class Node{
        char c;
        List<Node> neighbours;
        int inDegree;
        public Node(char c){
            this.c = c;
            neighbours = new ArrayList<Node>();
        }
    }
}
