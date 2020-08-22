package BucketSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/sort-characters-by-frequency/
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 */
/*
Running time is O(n)
Space needed is O(n)
 */
public class FrequencySort {
    public String frequencySort(String s) {
        HashMap<Character, Integer> freq = new HashMap<>();
        int maxFreq = 0;
        for(char c:s.toCharArray()){
            int charFreq = freq.getOrDefault(c, 0);
            freq.put(c, charFreq+1);
            maxFreq = Math.max(maxFreq, charFreq+1);
        }
        List<List<Character>> bucketSort = new ArrayList<>();
        for(int i = 0; i<=maxFreq; i++){
            bucketSort.add(new ArrayList<Character>());
        }
        for(Map.Entry<Character, Integer> entry:freq.entrySet()){
            bucketSort.get(entry.getValue()).add(entry.getKey());
        }
        StringBuilder sb = new StringBuilder();
        for(int i =maxFreq; i>0; i--){
            if(bucketSort.get(i).isEmpty()){
                continue;
            }
            for(char c: bucketSort.get(i)){
                for(int j = 0;j<i; j++){
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
