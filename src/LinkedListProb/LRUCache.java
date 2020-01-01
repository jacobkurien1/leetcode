package LinkedListProb;

import java.util.HashMap;
import java.util.Optional;

/*
https://leetcode.com/problems/lru-cache/
Design and implement a data structure for Least Recently Used (LRU) cache.
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present.
When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?
 */
/*
Solution: This can be done using a doubly linked list which acts as a queue and
a hashmap to give O(1) access to the nodes in the queue.
 */
public class LRUCache {
    private int capacity;
    private HashMap<Integer, DoublyLinkedListNode> map;
    DoublyLinkedListNode head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<Integer, DoublyLinkedListNode>();
    }

    public int get(int key) {
        if(map.containsKey(key)){
            // move the node to head
            moveNodeToHead(key, Optional.empty());
            return map.get(key).val;
        }
        return -1;
    }

    public void put(int key, int value) {

        if(!map.containsKey(key)) {
            // insert at head
            DoublyLinkedListNode insertNode = new DoublyLinkedListNode(key, value);
            if(head == null){
                head = insertNode;
                tail  = head;
            } else {
                head.next = insertNode;
                insertNode.prev = head;
                head = head.next;
            }
            map.put(key, insertNode);
        } else {
            //move the node to head
            moveNodeToHead(key, Optional.of(value));
        }

        if(map.size() > capacity){
            // evict from tail
            evictFromTail();
        }
    }

    void evictFromTail(){
        map.remove(tail.key);
        if(tail == head){
            head = null;
        }
        tail = tail.next;
        tail.prev = null;
    }

    void moveNodeToHead(int key, Optional<Integer> valOptional){
        DoublyLinkedListNode node = map.get(key);
        if(valOptional.isPresent()){
            node.val = valOptional.get(); // value can change for the same key
        }
        if(node.next == null){
            return; // node is already at head
        }
        if(node.prev == null){
            tail = tail.next; // node is at tail
            tail.prev = null;
        }
        node.next.prev = node.prev;
        if(node.prev != null) {
            node.prev.next = node.next;
        }

        head.next = node;
        node.prev = head;
        node.next = null;
        head = node;
    }

    class DoublyLinkedListNode {
        int key;
        int val;
        DoublyLinkedListNode prev;
        DoublyLinkedListNode next;
        public DoublyLinkedListNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
}
