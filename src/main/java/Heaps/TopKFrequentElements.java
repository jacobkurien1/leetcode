package Heaps;

import java.util.*;

/*
https://leetcode.com/problems/top-k-frequent-elements/
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {
    /*---------------------------------------------------------------------------------------------------------
    Algo1: Using a min heap and hashmap
    Runing time is O(nlog(k))
    Space O(n) for the hashmap
     ---------------------------------------------------------------------------------------------------------*/
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for(int i : nums){
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> Integer.compare(freq.get(a), freq.get(b)));
        for(int i: freq.keySet()){
            pq.add(i);
            if(pq.size()>k){
                pq.poll();
            }
        }
        List<Integer> topKFrequent = new ArrayList<Integer>();
        while(!pq.isEmpty()){
            topKFrequent.add(pq.poll());
        }
        Collections.reverse(topKFrequent);
        return topKFrequent;
    }
    /*---------------------------------------------------------------------------------------------------------
    Algo1: end
     ---------------------------------------------------------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------------------
    Algo2: Do a bucket sort, where in use a list<List<integer>> to store all the numbers and their frequency as the index of this list.
    Runing time is O(n)
    Space O(n) for the hashmap
     ---------------------------------------------------------------------------------------------------------*/

    public List<Integer> topKFrequentAlgo2(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for(int i : nums){
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }

        // initialize bucket's list of lists
        List<List<Integer>> bucket = new ArrayList<List<Integer>>(nums.length+1);
        for(int i = 0; i<=nums.length; i++){
            bucket.add(i, new ArrayList<Integer>());
        }

        for(int i: freq.keySet()){
            int numFreq = freq.get(i);
            bucket.get(numFreq).add(i);
        }
        List<Integer> topKFrequent = new ArrayList<Integer>();

        for(int i = nums.length; i>=0; i--){
            if(bucket.get(i)!=null){
                for(int num: bucket.get(i)){
                    if(topKFrequent.size()==k){
                        break;
                    }
                    topKFrequent.add(num);
                }
            }
        }

        return topKFrequent;
    }
    /*---------------------------------------------------------------------------------------------------------
    Algo2: end
     ---------------------------------------------------------------------------------------------------------*/

    /*
    Same algo as above, changing List<List<Integer>> to List<Integer>[]
     */
    public List<Integer> topKFrequentAlgo3(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for(int i : nums){
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length+1];
        for(int i: freq.keySet()){
            int numFreq = freq.get(i);
            if(bucket[numFreq] == null){
                bucket[numFreq] = new ArrayList<Integer>();
            }
            bucket[numFreq].add(i);
        }
        List<Integer> topKFrequent = new ArrayList<Integer>();

        for(int i = nums.length; i>=0; i--){
            if(bucket[i]!=null){
                for(int num: bucket[i]){
                    if(topKFrequent.size()==k){
                        break;
                    }
                    topKFrequent.add(num);
                }
            }
        }

        return topKFrequent;
    }
}
