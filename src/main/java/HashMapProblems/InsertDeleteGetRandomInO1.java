package HashMapProblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*
https://leetcode.com/problems/insert-delete-getrandom-o1/
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements.
Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
 */
/*
Use a hashmap with key as val and value as the index of the val in the ArrayList
To have remove at O(1) in the arraylist, swap the index to delete with the last index and delete the lastIndex.
 */
public class InsertDeleteGetRandomInO1 {
    Random rnd = new Random();
    HashMap<Integer, Integer> hm;
    ArrayList<Integer> list;
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomInO1() {
        hm = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(hm.containsKey(val)){
            return false;
        }
        hm.put(val, list.size());
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!hm.containsKey(val)){
            return false;
        }
        int index = hm.get(val);
        hm.remove(val);
        if(index != list.size() -1){
            int lastElem = list.get(list.size() -1);
            list.set(index, lastElem); // swap with last element
            hm.put(lastElem, index);
        }
        list.remove(list.size()-1); // remove the last element
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int rndIndex = rnd.nextInt(list.size());
        return list.get(rndIndex);
    }
}
