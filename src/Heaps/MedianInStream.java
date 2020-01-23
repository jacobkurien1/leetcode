package Heaps;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/find-median-from-data-stream/
Median is the middle value in an ordered integer list.
If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.


Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2


Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */
/*
Running time O(log(n)) for add and O(1) for findMedian
Space is O(n)
 */
public class MedianInStream {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    /** initialize your data structure here. */
    public MedianInStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a,b)-> Integer.compare(b,a));
    }

    public void addNum(int num) {
        if(!minHeap.isEmpty() && num > minHeap.peek()){
            minHeap.add(num);
            if(maxHeap.size() < minHeap.size()){
                maxHeap.add(minHeap.poll());
            }
        } else {
            maxHeap.add(num);
            if(maxHeap.size()-minHeap.size()>1){
                minHeap.add(maxHeap.poll());
            }
        }
    }


    public double findMedian() {
        if(maxHeap.size() == minHeap.size()){
            return (maxHeap.peek() + minHeap.peek())/2.0;
        } else {
            return maxHeap.peek();
        }
    }
}
