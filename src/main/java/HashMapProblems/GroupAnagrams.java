package HashMapProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
https://leetcode.com/problems/group-anagrams/
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.
 */
/*
Running time is O(N*k) where N is the number of strings and k is the avg length of a string
Space needed is O(N*k)
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> m = new HashMap<String, List<String>>();
        for(int i=0;i<strs.length; i++){
            String h = getHash(strs[i]);
            if(!m.containsKey(h)){
                m.put(h, new ArrayList<String>());
            }
            m.get(h).add(strs[i]);
        }

        return new ArrayList<List<String>>(m.values());
    }

    String getHash(String s){
        int[] h = new int[26];
        for(int i = 0;i<s.length(); i++ ){
            h[s.charAt(i) - 'a'] += 1;
        }
        return Arrays.toString(h);
    }
}
