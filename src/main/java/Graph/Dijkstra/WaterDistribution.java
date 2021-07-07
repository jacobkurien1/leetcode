package Graph.Dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/optimize-water-distribution-in-a-village/
There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.

For each house i, we can either build a well inside it directly with cost wells[i - 1] (note the -1 due to 0-indexing), or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes, where each pipes[j] = [house1j, house2j, costj] represents the cost to connect house1j and house2j together using a pipe. Connections are bidirectional.

Return the minimum total cost to supply water to all houses.



Example 1:



Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
Output: 3
Explanation:
The image shows the costs of connecting houses using pipes.
The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.


Constraints:

1 <= n <= 104
wells.length == n
0 <= wells[i] <= 105
1 <= pipes.length <= 104
pipes[j].length == 3
1 <= house1j, house2j <= n
0 <= costj <= 105
house1j != house2j
 */
/*
This is modified Dijkstra.
Running time is O((V+E)(log(V+E))
Space needed is O(V+E)
 */
public class WaterDistribution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        Graph g = new Graph(wells);
        PriorityQueue<Node> costs = new PriorityQueue<>((a, b)-> Integer.compare(a.cost, b.cost));
        for(Node node: g.hm.values()){
            costs.add(node);
        }
        for(int[] pipe: pipes){
            g.addEdge(pipe[0], pipe[1], pipe[2]);
        }
        int minCost = 0;
        HashSet<Integer> visited = new HashSet<>();
        while(!costs.isEmpty()){
            Node curr = costs.poll();
            minCost += curr.cost;
            visited.add(curr.id);
            for(Map.Entry<Node, Integer> childEntry: curr.children.entrySet()){
                Node child = childEntry.getKey();
                if(visited.contains(child.id)){
                    continue;
                }
                int cost = childEntry.getValue();
                if(child.cost> cost){
                    costs.remove(child);
                    child.cost = cost;
                    costs.add(child);
                }
            }
        }

        return minCost;
    }


    class Graph{
        HashMap<Integer, Node> hm = new HashMap<>();

        public Graph(int[] wells){
            for(int i = 0;i<wells.length;i++){
                hm.put(i, new Node(i, wells[i]));
            }
        }

        void addEdge(int u, int v, int cost){
            Node uNode = hm.get(u-1);
            Node vNode = hm.get(v-1);
            // the following is needed to make sure that we take min for duplicate edges
            uNode.children.put(vNode, Math.min(uNode.children.getOrDefault(vNode, Integer.MAX_VALUE), cost));
            vNode.children.put(uNode, Math.min(vNode.children.getOrDefault(uNode, Integer.MAX_VALUE), cost));
        }

    }

    class Node{
        int id;
        int cost;
        HashMap<Node, Integer> children = new HashMap<>();
        public Node(int id, int cost){
            this.id = id;
            this.cost = cost;
        }
    }
}
