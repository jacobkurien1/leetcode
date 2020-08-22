package Heaps;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Arrays;
/*
https://leetcode.com/problems/task-scheduler/
Given a char array representing tasks CPU need to do.
It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order.
Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks,
there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.



Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.


Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
 */
public class TaskScheduler {
    /*
    Algo1(NOt optimal) : use a maxHeap which compares on the freq.
    Keep polling the maxHeap and add the element in the Queue (if the freq >1, as we will reuse it).
    Calculate the minIntervals needed to schedule all the tasks
    Running time O(n + nlog(26)) = O(n).
    Space is O(26) = O(1)
     */
    public int leastInterval(char[] tasks, int n) {
        int[] taskMap = new int[26];
        for(char task : tasks){
            taskMap[task - 'A'] += 1;
        }
        PriorityQueue<CharFrequency> pq = new PriorityQueue<>((a, b)-> Integer.compare(b.freq, a.freq));
        for(int i = 0; i<26; i++){
            if(taskMap[i]!=0){
                pq.add(new CharFrequency((char)('A'+i), taskMap[i]));
            }
        }
        int minInterval = 0;
        Queue<CharFrequency> q = new LinkedList<>();
        while(!pq.isEmpty() || !q.isEmpty()){
            if(!pq.isEmpty()){
                CharFrequency cf = pq.poll();
                if(cf.freq >1){
                    cf.freq -=1;
                    cf.lastIndex = minInterval;
                    q.add(cf);
                }
                minInterval++;
            } else {
                minInterval++; // idle (not cleaned this code for better understanding)
            }

            while(!q.isEmpty() && minInterval - q.peek().lastIndex > n){
                pq.add(q.poll());
            }
        }
        return minInterval;
    }
    class CharFrequency{
        char c;
        int freq;
        int lastIndex;
        CharFrequency(char c, int freq){
            this.c = c;
            this.freq = freq;
        }
    }
    /*
    Algo1: end
     */

    /*
    Algo2(Optimized): Draw the grid. Find the highest freq element and then we will have atmost (highFreq-1)*n idlespots.
    Now calculate the number of elements that could be filled in these idle spots.
    Take a max of idlespots and the number of elements that needs to be filled.
    Also increment the number of intervals needed when you encounter an element which has same frequency as the highest freq element

    Running time is O(Max(26)+total number of tasks)
    Space needed is O(1) as we might need atmost 26 spaces
     */
    public int leastIntervalAlog2(char[] tasks, int n) {
        int[] taskFreq = new int[26];
        for(char task : tasks){
            taskFreq[task - 'A']++;
        }
        Arrays.sort(taskFreq);
        if(taskFreq[25] == 0){
            return 0;
        }
        //get the max and add the idle slots
        int minInterval = taskFreq[25];
        int maxIdleSpots = (taskFreq[25] - 1)*n;
        int numOfElementsLeft = 0;
        for(int i = 24; i>=0 && taskFreq[i]!= 0; i--){
            if(taskFreq[i] == taskFreq[25]){
                minInterval++;
                taskFreq[i]--;
            }
            numOfElementsLeft += taskFreq[i];
        }
        return minInterval + Math.max(maxIdleSpots, numOfElementsLeft);
    }
}
