package StringProblems;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/reorganize-string/
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
 */
/*
Running time is O(n + AlogA) where N is the number of elements in the string and A is the alphabet size.
Since the alphabet size is constant, the running time is O(n)

Space needed is O(A) = O(1) since we only store the hashmap and priority queue with A elements.
Note: if we consider the output, we will have to use O(N) for storing the output
 */
public class ConvertStringToNotHaveAdjacentDuplicates {
    public String reorganizeString(String S) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for(int i = 0; i<S.length(); i++){
            freqMap.put(S.charAt(i), freqMap.getOrDefault(S.charAt(i), 0)+1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.getValue(), a.getValue()));
        for(Map.Entry<Character, Integer> entry : freqMap.entrySet()){
            pq.add(entry);
        }

        if(pq.peek().getValue() > Math.ceil(S.length()/2.0)){
            return ""; // we cannot reorganize the string
        }
        char[] ret = new char[S.length()];
        int retIndex = 0;
        while(!pq.isEmpty()){
            Map.Entry<Character, Integer> entry = pq.poll();
            for(int i = 0; i<entry.getValue(); i++){
                ret[retIndex] = entry.getKey();
                retIndex += 2;
                if(retIndex >=S.length()){
                    retIndex = 1;
                }
            }
        }
        return new String(ret);
    }
}
