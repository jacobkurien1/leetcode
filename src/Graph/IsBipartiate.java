package Graph;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

/*
https://leetcode.com/problems/is-graph-bipartite/
Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every
edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
Each node is an integer between 0 and graph.length - 1.
There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation:
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation:
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.


Note:

graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 */
/*
This is similar to can the graph be colored by 2 colors such that no 2 adjacent nodes are having the same color
Running time is O(V+E) where v is the number of vertex and E is the number of edges
Space needed is O(V) to store all the vertices
 */
public class IsBipartiate {
    public boolean isBipartite(int[][] graph) {
        if(graph == null || graph.length == 0){
            return false;
        }
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for(int i = 0; i<graph.length; i++){
            if(!isVisited(set1, set2, i) && !bfsIsBipatiate(graph, set1, set2, i)){
                return false;
            }
        }
        return true;
    }

    boolean isVisited( HashSet<Integer> set1, HashSet<Integer> set2, int vertex){
        return set1.contains(vertex) || set2.contains(vertex);
    }

    boolean bfsIsBipatiate(int[][] graph, HashSet<Integer> set1, HashSet<Integer> set2, int start){
        Deque<Integer> dq = new LinkedList<>();
        dq.addFirst(start);
        set1.add(start);
        boolean isSet1Current = false;
        while(!dq.isEmpty()){
            int levelSize = dq.size();
            for(int i = 0; i<levelSize; i++){
                int vertex = dq.pollLast();
                for(int neighbour : graph[vertex]){
                    if(!set1.contains(neighbour) && !set2.contains(neighbour)){
                        //this node has never been visted before
                        dq.addFirst(neighbour);
                    }
                    if(isSet1Current){
                        if(set2.contains(neighbour)){
                            return false;
                        }
                        set1.add(neighbour);
                    } else {
                        if(set1.contains(neighbour)){
                            return false;
                        }
                        set2.add(neighbour);
                    }
                }
            }
            isSet1Current = !isSet1Current;
        }
        return true;
    }
}
