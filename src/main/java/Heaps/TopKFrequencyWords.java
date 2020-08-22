package Heaps;

import java.util.*;

/*
https://leetcode.com/problems/top-k-frequent-words/
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest.
If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
 */
/*
Algo 1: Using priorityQueue
Running time is O(nlogk)
Space is O(n)
 */
public class TopKFrequencyWords {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> hm = new HashMap<>();
        for(String word : words){
            int val = hm.getOrDefault(word, 0);
            hm.put(word, val+1);
        }

        PriorityQueue<WordWithFreq> pq = new PriorityQueue<>((a, b)->{
            if(a.freq == b.freq){
                return b.str.compareTo(a.str);
            } else {
                return Integer.compare(a.freq, b.freq);
            }
        });

        for(Map.Entry<String, Integer> entry : hm.entrySet()){
            pq.add(new WordWithFreq(entry.getKey(), entry.getValue()));
            if(pq.size()>k){
                pq.poll();
            }
        }

        List<String> lst = new ArrayList<>();
        while (!pq.isEmpty()){
            lst.add(pq.poll().str);
        }
        Collections.reverse(lst);
        return lst;
    }

    class WordWithFreq{
        String str;
        int freq;
        public WordWithFreq(String str, int freq){
            this.str = str;
            this.freq = freq;
        }
    }
}
