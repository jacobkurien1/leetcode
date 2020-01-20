package Deque;

import java.util.Deque;
import java.util.LinkedList;

/*
https://leetcode.com/problems/design-hit-counter/
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the
system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:

HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301);
Follow up:
What if the number of hits per second could be very large? Does your design scale?
 */
/*
Running time for hit is O(1)
Running time for getHits is O(n) where n is the total number of timestamps where the hits occurred(in worst case).
 */
public class HitCounter {
    int totalHits = 0;
    Deque<Hit> deque;
    /** Initialize your data structure here. */
    public HitCounter() {
        deque = new LinkedList<Hit>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        if(!deque.isEmpty() && deque.peekLast().timestamp == timestamp){
            deque.peekLast().freq++;
        } else {
            deque.addLast(new Hit(timestamp, 1));
        }
        totalHits++;

    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(!deque.isEmpty() && timestamp - deque.peekFirst().timestamp >=300){
            totalHits -= deque.pollFirst().freq;
        }
        return totalHits;
    }

    class Hit{
        int timestamp;
        int freq;
        Hit(int timestamp, int freq){
            this.timestamp = timestamp;
            this.freq = freq;
        }
    }
}
