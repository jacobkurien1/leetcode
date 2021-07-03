package main.java.UnionFind;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 * 947. Most Stones Removed with Same Row or Column
 * Medium
 *
 * 1555
 *
 * 465
 *
 * Add to List
 *
 * Share
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 *
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 *
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Explanation: One way to remove 5 stones is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,1].
 * 2. Remove stone [2,1] because it shares the same column as [0,1].
 * 3. Remove stone [1,2] because it shares the same row as [1,0].
 * 4. Remove stone [1,0] because it shares the same column as [0,0].
 * 5. Remove stone [0,1] because it shares the same row as [0,0].
 * Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
 * Example 2:
 *
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Explanation: One way to make 3 moves is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,0].
 * 2. Remove stone [2,0] because it shares the same column as [0,0].
 * 3. Remove stone [0,2] because it shares the same row as [0,0].
 * Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
 * Example 3:
 *
 * Input: stones = [[0,0]]
 * Output: 0
 * Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 *
 *
 * Constraints:
 *
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 104
 * No two stones are at the same coordinate point.
 */
/*
You can use union find. this problem is equivalent to the minimum number of islands problems.
Running time is O(N) where N is the number of stones. Since we are using union-find with path compression and rank optimization
Space needed is O(N)
 */
public class MostStonesRemoved {
    public int removeStones(int[][] stones) {
        UnionFind uf = new UnionFind();
        for(int i =0; i<stones.length; i++){
            uf.union("r"+Integer.toString(stones[i][0]), "c"+Integer.toString(stones[i][1]));
        }
        return stones.length - uf.getIslandCount();
    }

    class UnionFind{

        HashMap<String, String> parent = new HashMap<>();
        HashMap<String, Integer> rank = new HashMap<>();
        int islands = 0;

        String find(String n) {
            if(!parent.containsKey(n)){
                parent.put(n, n);
                rank.put(n, 0);
                ++islands;
                return n;
            }
            if(parent.get(n) != n){
                parent.put(n, find(parent.get(n)));
            }
            return parent.get(n);
        }

        public int getIslandCount(){
            return islands;
        }

        void union(String u, String v){
            String rootU = find(u);
            String rootV = find(v);
            if(!rootU.equals(rootV)){
                --islands;
                if(rank.get(rootU) > rank.get(rootV)){
                    parent.put(rootV, rootU);
                } else {
                    parent.put(rootU, rootV);
                    if(rank.get(rootU) == rank.get(rootV)){
                        rank.put(rootV, rank.get(rootV)+1);
                    }
                }
            }
        }
    }
}
