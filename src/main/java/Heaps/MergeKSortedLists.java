package Heaps;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/merge-k-sorted-lists
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
 */
/*
Solution: use a priority queue as a min heap.
Running time O(nlogk) where n is the length of all nodes in all the lists
Space needed is O(k) for the priority queue
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode mergedList = null;
        ListNode curr =null;
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>((a, b) -> Integer.compare(a.val, b.val));
        for(int i = 0; i<lists.length; i++){
            if(lists[i] != null){
                pq.add(lists[i]);
            }
        }
        while(!pq.isEmpty()){
            if(curr == null){
                curr = pq.poll();
                mergedList = curr;
            } else{
                curr.next = pq.poll();
                curr = curr.next;
            }
            if(curr.next != null){
                pq.add(curr.next);
            }
        }
        return mergedList;
    }
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
