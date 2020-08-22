package Graph;

import java.util.*;

/*
https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges.
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
/*
Running time is O(V+E)
Space needed is O(V) for the call stack in dfs and keeping track of visited nodes
 */
public class ConnectedComponentInUG {
    public int countComponents(int n, int[][] edges) {
        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        for(int[] edge: edges){
            List<Integer> v1AdjList = hm.getOrDefault(edge[0], new ArrayList<Integer>());
            List<Integer> v2AdjList = hm.getOrDefault(edge[1], new ArrayList<Integer>());
            v1AdjList.add(edge[1]);
            v2AdjList.add(edge[0]);

            hm.put(edge[0], v1AdjList);
            hm.put(edge[1], v2AdjList);
        }

        Set<Integer> visited = new HashSet<>();
        int compCount = 0;
        for(int key : hm.keySet()){
            if(!visited.contains(key)){
                compCount++;
                dfs(hm, visited, key);
            }
        }
        return compCount + (n-hm.size());// second component to take care of vertex that does not have any edges
    }

    void dfs(HashMap<Integer, List<Integer>> hm, Set<Integer> visited, int vertex){
        visited.add(vertex);
        for(int neighbour : hm.get(vertex)){
            if(!visited.contains(neighbour)){
                dfs(hm, visited, neighbour);
            }
        }
    }
}
