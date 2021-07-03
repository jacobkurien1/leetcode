package main.java.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
https://leetcode.com/problems/critical-connections-in-a-network/
There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network
where connections[i] = [a, b] represents a connection between servers a and b.
Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

Return all critical connections in the network in any order.



Example 1:



Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.


Constraints:

1 <= n <= 10^5
n-1 <= connections.length <= 10^5
connections[i][0] != connections[i][1]
There are no repeated connections.
 */
/*
We will use Tarjan's algorithm here
Any edge part of a cycle is not a critical edge.
do dfs and track order and least node that can be reached.
if the least node between 2 edge is different, that means they are part of different cycle and that edge will be a critical connection.
Time complexity will be O(V+E)
Space needed is O(V)
 */
public class CriticalConnectionsInNetwork {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Graph connectionGraph = new Graph();
        for(int i = 0;i<n; i++){
            connectionGraph.addVertex(i);
        }
        for(List<Integer> edge : connections){
            connectionGraph.addEdge(edge.get(0), edge.get(1));
        }
        HashMap<Integer, Integer> visitOrder = new HashMap<>();
        HashMap<Integer, Integer> leastNodeReachableMap = new HashMap<>();
        List<List<Integer>> criticalEdges = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(!leastNodeReachableMap.containsKey(i)){
                dfs(i, -1, leastNodeReachableMap, connectionGraph, criticalEdges);
            }
        }
        return criticalEdges;
    }

    int order = 0;

    int dfs(int i,
            int parent,
            HashMap<Integer, Integer> leastNodeReachableMap,
            Graph connectionGraph, List<List<Integer>> criticalEdges){
        order++;
        leastNodeReachableMap.put(i, order);
        int leastNodeReachable = order;
        for (Node n:connectionGraph.nodeMap.get(i).neighbours){
            if(n.id == parent){
                continue; // you cannot go back in the path you came
            }
            int leastNodeReachableForN;
            if(!leastNodeReachableMap.containsKey(n.id)){
                leastNodeReachableForN = dfs(n.id, i, leastNodeReachableMap, connectionGraph, criticalEdges);
            }else{
                leastNodeReachableForN = leastNodeReachableMap.get(n.id);
            }
            if(leastNodeReachable < leastNodeReachableForN){
                criticalEdges.add(new ArrayList<>(Arrays.asList(i, n.id)));
            }
            leastNodeReachable = Math.min(leastNodeReachable, leastNodeReachableForN);
        }
        leastNodeReachableMap.put(i, leastNodeReachable);
        return leastNodeReachable;
    }

    class Graph{
        HashMap<Integer, Node> nodeMap;
        public Graph(){
            nodeMap = new HashMap<>();
        }

        void addEdge(int u, int v){
            addVertex(u);
            addVertex(v);
            Node uNode = nodeMap.get(u);
            Node vNode = nodeMap.get(v);
            uNode.neighbours.add(vNode);
            vNode.neighbours.add(uNode);
        }

        void addVertex(int v){
            if(!nodeMap.containsKey(v)){
                nodeMap.put(v, new Node(v));
            }
        }
    }

    class Node {
        int id;
        List<Node> neighbours;
        public Node(int id){
            this.id = id;
            neighbours = new ArrayList<>();
        }
    }
}
