package main.java.Greedy;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/partition-labels/
A string S of lowercase English letters is given. We want to partition this string into as many parts as possible
so that each letter appears in at most one part, and return a list of integers representing the size of these parts.



Example 1:

Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.


Note:

S will have length in range [1, 500].
S will consist of lowercase English letters ('a' to 'z') only.
 */
/*
Running time is O(n)
Space needed is O(1)
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        List<Integer> ret = new ArrayList<>();
        int[] last = new int[26];
        for (int i = 0; i<S.length(); i++){
            last[S.charAt(i)-'a'] = i;
        }
        int lastIndexForPartition = 0;
        int startOfPartition = 0;
        for(int i = 0; i<S.length(); i++){
            lastIndexForPartition = Math.max(lastIndexForPartition, last[S.charAt(i) - 'a']);
            if(lastIndexForPartition == i){
                ret.add(lastIndexForPartition - startOfPartition+ 1);
                startOfPartition = i+1;
            }
        }
        return ret;
    }
}
