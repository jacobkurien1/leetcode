package TopologicalSort;

import java.util.*;

/*
https://leetcode.com/problems/parallel-courses-ii/
Given the integer n representing the number of courses at some university labeled from 1 to n, and the array dependencies where dependencies[i] = [xi, yi]
represents a prerequisite relationship, that is, the course xi must be taken before the course yi.  Also, you are given the integer k.

In one semester you can take at most k courses as long as you have taken all the prerequisites for the courses you are taking.

Return the minimum number of semesters to take all courses. It is guaranteed that you can take all courses in some way.



Example 1:



Input: n = 4, dependencies = [[2,1],[3,1],[1,4]], k = 2
Output: 3
Explanation: The figure above represents the given graph. In this case we can take courses 2 and 3 in the first semester,
then take course 1 in the second semester and finally take course 4 in the third semester.
Example 2:



Input: n = 5, dependencies = [[2,1],[3,1],[4,1],[1,5]], k = 2
Output: 4
Explanation: The figure above represents the given graph. In this case one optimal way to take all courses is:
take courses 2 and 3 in the first semester and take course 4 in the second semester,
then take course 1 in the third semester and finally take course 5 in the fourth semester.
Example 3:

Input: n = 11, dependencies = [], k = 2
Output: 6


Constraints:

1 <= n <= 15
1 <= k <= n
0 <= dependencies.length <= n * (n-1) / 2
dependencies[i].length == 2
1 <= xi, yi <= n
xi != yi
All prerequisite relationships are distinct, that is, dependencies[i] != dependencies[j].
The given graph is a directed acyclic graph.
 */
/*
Running time is O(V+E + Vlog(V))
Space needed is O(V)
 */
public class ParallelCoursesForSemester {
    public int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        Graph g = new Graph();
        for(int i = 1; i<=n; i++){
            g.addNode(i);
        }

        for(int edge = 0; edge<dependencies.length; edge++){
            g.addEdge(dependencies[edge][0], dependencies[edge][1]);
        }

        PriorityQueue<Node> q = new PriorityQueue<>((a, b)-> Integer.compare(b.neighbours.size(), a.neighbours.size()));
        for(Node node: g.allNodes.values()){
            if(node.inDegree == 0){
                q.add(node);
            }
        }

        int semCount =0;
        while(!q.isEmpty()){
            semCount++;
            int qSize = q.size();
            List<Node> nodesToAdd = new ArrayList<>();
            for(int i = 0; i<Math.min(k,qSize); i++){
                Node node = q.poll();
                for(Node neighbour: node.neighbours){
                    neighbour.inDegree -= 1;
                    if(neighbour.inDegree == 0){
                        nodesToAdd.add(neighbour);
                    }
                }
            }
            for(Node nodeToAdd:nodesToAdd){
                q.add(nodeToAdd);
            }
        }
        return semCount;
    }

    class Graph{
        Map<Integer, Node> allNodes = new HashMap<>();

        void addEdge(int u, int v){
            Node uNode = allNodes.get(u);
            Node vNode = allNodes.get(v);
            uNode.neighbours.add(vNode);
            vNode.inDegree +=1;
        }

        void addNode(int n){
            allNodes.put(n, new Node(n));
        }
    }
    class Node{
        int val;
        int inDegree = 0;
        List<Node> neighbours = new ArrayList<>();
        Node(int val){
            this.val = val;
        }
    }
}
