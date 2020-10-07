package main.java.Graph;

import java.util.*;

/*
https://leetcode.com/problems/bus-routes/
We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7],
this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T.
Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.

Example:
Input:
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation:
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.


Constraints:

1 <= routes.length <= 500.
1 <= routes[i].length <= 10^5.
0 <= routes[i][j] < 10 ^ 6.
 */
/*
We will do bfs here and the buses will be considered as the nodes of bfs
Running time is O(n^2) where n is the number of buses
Space needed is O(n^2) as well
 */
public class BusRoutesMinimizeBusChanges {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(S == T){
            return 0;
        }
        BusGraph busGraph = new BusGraph();
        int busKey = 0;
        for(int[] route:routes){
            busGraph.add(new Bus(++busKey, route));
        }

        Queue<Bus> bfsQ = new LinkedList<>();
        for(Bus st: busGraph.busStopMap.getOrDefault(S, new ArrayList<>())){
            bfsQ.add(st);
        }

        int numOfBuses = 1;
        HashSet<Integer> visitedBus=  new HashSet<>();
        while(!bfsQ.isEmpty()){
            int qSize = bfsQ.size();
            for(int i = 0; i< qSize; i++){
                Bus bus = bfsQ.poll();
                if(bus.stops.contains(T)){
                    return numOfBuses;
                }
                if(visitedBus.contains(bus.key)){
                    continue;
                }
                visitedBus.add(bus.key);
                for(Bus next: bus.busesInSameRoute){
                    bfsQ.add(next);
                }
            }
            numOfBuses++;
        }

        return -1;
    }

    class BusGraph{
        HashMap<Integer, List<Bus>> busStopMap = new HashMap<>();

        void add(Bus bus){
            for(int stop: bus.stops){
                List<Bus> busesForStop = busStopMap.getOrDefault(stop, new ArrayList<>());
                for(Bus busInRoute: busesForStop){
                    addOnRouteBuses(bus, busInRoute);
                }
                busesForStop.add(bus);
                busStopMap.put(stop, busesForStop);
            }
        }

        void addOnRouteBuses(Bus bus1, Bus bus2){
            bus1.add(bus2);
            bus2.add(bus1);
        }
    }

    class Bus{
        HashSet<Integer> stops;
        int key;
        public Bus(int key, int[] stopsArr){
            busesInSameRoute = new ArrayList<>();
            stops = new HashSet<>();
            this.key = key;
            for(int stop:stopsArr){
                stops.add(stop);
            }
        }
        List<Bus> busesInSameRoute;

        void add(Bus busInSameRoute){
            busesInSameRoute.add(busInSameRoute);
        }
    }
}
