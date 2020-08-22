package LinkedListProb;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/*
https://leetcode.com/problems/all-oone-data-structure/
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1.
If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.
 */
/*
we use a doubly linked list arranged with bucket in ascending order of frequency.
Running time for all operation is O(1)
 */
public class AllOoneDataStructure {
    Bucket head = null;
    Bucket tail = null;
    HashMap<String, Integer> keyFreq;
    HashMap<Integer, Bucket> bucketMap;
    /** Initialize your data structure here. */
    public AllOoneDataStructure() {
        keyFreq = new HashMap<>();
        bucketMap = new HashMap<>();
        head = new Bucket(0);
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
        bucketMap.put(0, head);
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int originalFreq = keyFreq.getOrDefault(key, 0);
        int newFreq = originalFreq +1;
        keyFreq.put(key, newFreq);
        insertKeyIntoBucket(newFreq, key, originalFreq);
        if(originalFreq != 0){
            removeKeyFromBucket(originalFreq, key);
        }
    }

    void removeKeyFromBucket(int freq, String key){
        Bucket bucketWithFreq = bucketMap.get(freq);
        bucketWithFreq.keys.remove(key);
        if(bucketWithFreq.keys.isEmpty()){
            bucketMap.remove(freq);
            bucketWithFreq.prev.next = bucketWithFreq.next;
            bucketWithFreq.next.prev = bucketWithFreq.prev;
        }
    }

    void insertKeyIntoBucket(int freq, String key, int originalFreq){
        if(bucketMap.containsKey(freq)){
            Bucket bucketWithFreq = bucketMap.get(freq);
            bucketWithFreq.keys.add(key);
        } else {
            Bucket bucketWithFreq = new Bucket(freq);
            bucketWithFreq.keys.add(key);
            bucketMap.put(freq, bucketWithFreq);

            Bucket bucketWithOrigFreq = bucketMap.get(originalFreq);
            Bucket nextFromOrigFreq = bucketWithOrigFreq.next;

            bucketWithOrigFreq.next = bucketWithFreq;
            bucketWithFreq.prev = bucketWithOrigFreq;

            nextFromOrigFreq.prev = bucketWithFreq;
            bucketWithFreq.next = nextFromOrigFreq;
        }

    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(keyFreq.containsKey(key)){
            int originalFreq = keyFreq.get(key);
            int newFreq = originalFreq-1;
            if(newFreq == 0){
                keyFreq.remove(key);
            } else{
                keyFreq.put(key, newFreq);
                insertKeyIntoBucket(newFreq, key, originalFreq);
            }
            removeKeyFromBucket(originalFreq, key);
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return (tail.prev == head ?"":tail.prev.keys.iterator().next());
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return (head.next == tail ?"":head.next.keys.iterator().next());
    }

    class Bucket{
        Set<String> keys;
        int val;
        Bucket next;
        Bucket prev;
        Bucket(int val){
            this.val =val;
            keys = new LinkedHashSet<>(); // linkedhashset for O(1) iterator().next() call
        }
    }
}
