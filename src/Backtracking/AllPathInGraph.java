package Backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
https://leetcode.com/problems/all-paths-from-source-to-target/
Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []]
Output: [[0,1,3],[0,2,3]]
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.
 */
/*
Running time is O(2^N * N) 2^N different path and N is time needed to clone one path
Space needed is O(2^N * N) 2^N different paths and each path can have N elements
 */
public class AllPathInGraph {
    List<List<Integer>> allPaths = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if(graph == null){
            return allPaths;
        }
        getPath(graph, 0, graph.length-1, new LinkedList<Integer>());
        return allPaths;
    }

    void getPath(int[][] graph, int curr, int end, List<Integer> path){
        path.add(curr);
        if(curr == end){
            allPaths.add(new ArrayList<Integer>(path));
        } else {
            for(int neighbour : graph[curr]){
                getPath(graph, neighbour, end, path);
            }
        }
        path.remove(path.size()-1);
    }
}
