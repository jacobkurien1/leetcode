package main.java.TopologicalSort;

import java.util.*;

/*
https://leetcode.com/problems/course-schedule-ii
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */
/*
Here we will do Kahn's topological sort
Running time is O(E + V)
Space needed is O(V)
 */
public class CourseScheduling {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph g = new Graph();
        g.addAllNodes(numCourses);
        for(int[] edge: prerequisites){
            g.addEdge(edge[1], edge[0]);
        }
        List<Integer> topologicalOrder = new ArrayList<>();
        Queue<Node> q = new LinkedList<>(g.nodesWithoutIndegree());
        while(!q.isEmpty()){
            Node curr = q.poll();
            topologicalOrder.add(curr.id);
            for(Node neighbour : curr.neighbours){
                neighbour.indegree -= 1;
                if(neighbour.indegree == 0){
                    q.add(neighbour);
                }
            }
        }
        if(topologicalOrder.size() != numCourses){
            return new int[0];
        }
        int[] courseOrder = new int[numCourses];
        int courseOrderIndex = 0;
        for(int order: topologicalOrder){
            courseOrder[courseOrderIndex++] = order;
        }
        return courseOrder;
    }

    class Graph {
        Map<Integer, Node> allNodes = new HashMap<>();

        void addAllNodes(int len){
            for(int i=0; i<len;i++){
                allNodes.put(i, new Node(i));
            }
        }

        void addEdge(int st, int end){
            allNodes.get(st).add(allNodes.get(end));
            allNodes.get(end).indegree +=1;
        }

        List<Node> nodesWithoutIndegree(){
            List<Node> nodes = new ArrayList<>();
            for(Node n : allNodes.values()){
                if(n.indegree == 0){
                    nodes.add(n);
                }
            }
            return nodes;
        }
    }

    class Node {
        int id;
        List<Node> neighbours;
        int indegree = 0;
        public Node(int id){
            this.id = id;
            neighbours = new ArrayList<>();
        }
        public void add(Node n){
            neighbours.add(n);
        }
    }
}
