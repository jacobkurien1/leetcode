package Heaps;

import java.util.PriorityQueue;
import java.util.Arrays;

/*
https://leetcode.com/problems/campus-bikes/
On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike)
pair with the shortest Manhattan distance between each other, and assign the bike to that worker.
(If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index;
if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.



Example 1:



Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: [1,0]
Explanation:
Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
Example 2:



Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: [0,2,1]
Explanation:
Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2,
thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].


Note:

0 <= workers[i][j], bikes[i][j] < 1000
All worker and bike locations are distinct.
1 <= workers.length <= bikes.length <= 1000
 */
/*
Use a priority queue and add workerbikepair and keep polling to get all the worker bike assignment
Running time is O(n*m log(n*m)) where n is the number of workers and m is the number of bikes.
Space is O(n*m) for the priority queue.

Algo2: If we know the maxDistance, which we do in this case(2000), we can use bucket sort(counting sort) in O(n*m) time.
Space is O(2000)
 */
public class WorkerBikeMatch {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        PriorityQueue<WorkerBikePair> pq = new PriorityQueue<>();
        for(int workerIndex = 0; workerIndex <workers.length; workerIndex++){
            for(int bikeIndex = 0; bikeIndex < bikes.length; bikeIndex++){
                pq.add(new WorkerBikePair(workerIndex, workers[workerIndex], bikeIndex, bikes[bikeIndex]));
            }
        }
        int[] bikeAssignment = new int[workers.length];
        Arrays.fill(bikeAssignment, -1);
        boolean[] bikeUsed = new boolean[bikes.length];
        while(!pq.isEmpty()){
            WorkerBikePair wbp = pq.poll();
            if(bikeAssignment[wbp.workerIndex] == -1 && !bikeUsed[wbp.bikeIndex]){
                bikeAssignment[wbp.workerIndex] = wbp.bikeIndex;
                bikeUsed[wbp.bikeIndex] = true;
            }
        }
        return bikeAssignment;
    }

    class WorkerBikePair implements Comparable<WorkerBikePair>{
        int workerIndex;
        int bikeIndex;
        int[] worker;
        int[] bike;

        WorkerBikePair(int workerIndex, int[] worker, int bikeIndex, int[] bike){
            this.workerIndex = workerIndex;
            this.worker = worker;
            this.bikeIndex = bikeIndex;
            this.bike = bike;
        }

        int getDistance(){
            return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
        }

        @Override
        public int compareTo(WorkerBikePair other){
            if(getDistance() != other.getDistance()){
                return Integer.compare(getDistance(), other.getDistance());
            }
            if(workerIndex != other.workerIndex){
                return Integer.compare(workerIndex, other.workerIndex);
            }
            return Integer.compare(bikeIndex, other.bikeIndex);
        }
    }
}
