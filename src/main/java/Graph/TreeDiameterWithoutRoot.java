package Graph;

import java.util.*;

/*
https://leetcode.com/problems/tree-diameter/
Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.

The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.



Example 1:



Input: edges = [[0,1],[0,2]]
Output: 2
Explanation:
A longest path of the tree is the path 1 - 0 - 2.
Example 2:



Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
Output: 4
Explanation:
A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.


Constraints:

0 <= edges.length < 10^4
edges[i][0] != edges[i][1]
0 <= edges[i][j] <= edges.length
The given edges form an undirected tree.
 */
/*
Running time is O(n), edges in graph which is a tree is n
Space needed is O(n)
 */
public class TreeDiameterWithoutRoot {
    public int treeDiameter(int[][] edges) {
        if(edges.length == 0){
            return 0;
        }
        Graph g = new Graph();
        for(int r = 0; r<edges.length; r++){
            g.addEdge(edges[r][0], edges[r][1]);
        }
        return g.getDiameter();
    }

    class Graph{
        Map<Integer, Node> allNodes = new HashMap<Integer, Node>();

        void addEdge(int u, int v){
            if(!allNodes.containsKey(u)){
                allNodes.put(u, new Node(u));
            }
            if(!allNodes.containsKey(v)){
                allNodes.put(v, new Node(v));
            }
            allNodes.get(u).neighbours.add(allNodes.get(v));
            allNodes.get(v).neighbours.add(allNodes.get(u));
        }

        /*
        We do BFS twice here.
        First Bfs and get the maxDepth and MaxDepthNode
        Second BFS starting from the maxDepthNode and get the maxDepth.

        We can prove by contradiction that the maxDepthNode found in the first step is the start point of the diameter
        */
        int getDiameter(){
            Node n = allNodes.get(0); // Assumption that 0th Node is present
            bfs(n);
            return bfs(maxDepthNode);
        }

        int bfs(Node n){
            if(n == null){
                return 0;
            }
            Queue<Node> q = new LinkedList<>();
            HashSet<Integer> visited = new HashSet<>();
            q.add(n);
            visited.add(n.data);
            int level = 0;
            while(!q.isEmpty()){
                int qSize = q.size();
                level++;
                for(int i=0;i<qSize;i++){
                    n = q.poll();
                    maxDepthNode = n;
                    for(Node neighbour:n.neighbours){
                        if(!visited.contains(neighbour.data)){
                            q.add(neighbour);
                            visited.add(neighbour.data);
                        }
                    }
                }
            }
            return level-1;
        }

        Node maxDepthNode = null;
    }

    class Node{
        int data;
        List<Node> neighbours;
        Node(int data){
            this.data = data;
            neighbours = new ArrayList<Node>();
        }
    }
}
