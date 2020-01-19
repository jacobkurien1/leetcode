package HashMapProblems;

import java.util.*;

/*
https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements.
The probability of each element being returned is linearly related to the number of same value the collection contains.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
 */
public class InsertDeleteGetRandomInO1WithDuplicates {
    HashMap<Integer, Set<Integer>> hm;
    List<Integer> list;
    Random rnd = new Random();
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomInO1WithDuplicates() {
        hm = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean isValuePresent = hm.containsKey(val);
        list.add(val);
        if(!hm.containsKey(val)){
            hm.put(val, new HashSet<Integer>());
        }
        hm.get(val).add(list.size()-1);
        return isValuePresent;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!hm.containsKey(val)){
            return false;
        }
        int indexToRemove = hm.get(val).iterator().next();
        hm.get(val).remove(indexToRemove);
        if(hm.get(val).isEmpty()){
            hm.remove(val);
        }
        if(indexToRemove != list.size()-1){
            int lastVal = list.get(list.size()-1);
            hm.get(lastVal).remove(list.size()-1);
            hm.get(lastVal).add(indexToRemove);
            list.set(indexToRemove, lastVal);
        }
        list.remove(list.size()-1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rnd.nextInt(list.size()));
    }
}
