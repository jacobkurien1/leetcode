package Queue;

import java.util.Deque;
import java.util.LinkedList;

/*
https://leetcode.com/problems/moving-average-from-data-stream/
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
 */
/*
Running time is O(1)
Space is O(size)
 */
public class MovingAvgSlidingWindow {
    Deque<Integer> dq;
    int size;
    int sum;
    /** Initialize your data structure here. */
    public MovingAvgSlidingWindow(int size) {
        dq = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        dq.addLast(val);
        sum += val;
        if(dq.size()>size){
            sum -= dq.pollFirst();
        }
        return sum/(double)dq.size();
    }
}
