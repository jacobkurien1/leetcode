package SortingProblems;

import java.util.Arrays;
import java.util.LinkedList;

/*
https://leetcode.com/problems/queue-reconstruction-by-height/
You are given an array of people, people, which are the attributes of some people in a queue (not necessarily in order). Each people[i] = [hi, ki] represents the ith person of height hi with exactly ki other people in front who have a height greater than or equal to hi.

Reconstruct and return the queue that is represented by the input array people. The returned queue should be formatted as an array queue, where queue[j] = [hj, kj] is the attributes of the jth person in the queue (queue[0] is the person at the front of the queue).



Example 1:

Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
Explanation:
Person 0 has height 5 with no other people taller or the same height in front.
Person 1 has height 7 with no other people taller or the same height in front.
Person 2 has height 5 with two persons taller or the same height in front, which is person 0 and 1.
Person 3 has height 6 with one person taller or the same height in front, which is person 1.
Person 4 has height 4 with four people taller or the same height in front, which are people 0, 1, 2, and 3.
Person 5 has height 7 with one person taller or the same height in front, which is person 1.
Hence [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] is the reconstructed queue.
Example 2:

Input: people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
Output: [[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]


Constraints:

1 <= people.length <= 2000
0 <= hi <= 106
0 <= ki < people.length
It is guaranteed that the queue can be reconstructed.
 */
/*
Running time is O(n^2)
Space needed is O(n)
 */
public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if(a[1] == b[1]){
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        LinkedList<int[]> peopleList = new LinkedList<>();
        for(int[] entry: people){
            int gOrECount = 0;
            boolean isInserted = false;
            for(int i =0; i < peopleList.size(); i++){
                if(peopleList.get(i)[0] >= entry[0]){
                    ++gOrECount;
                }
                if(gOrECount > entry[1]){
                    isInserted = true;
                    peopleList.add(i, entry);
                    break;
                }
            }
            if(!isInserted){
                peopleList.add(entry);
            }
        }
        int[][] ret = new int[people.length][people[0].length];
        peopleList.toArray(ret);
        return ret;
    }
}
