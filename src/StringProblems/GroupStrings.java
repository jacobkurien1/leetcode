package StringProblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
https://leetcode.com/problems/group-shifted-strings/
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
 */
/*
Running time is O(n*A) where n is the number of strings and A is the avg length of the string
Space needed is O(n)
 */
public class GroupStrings {
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> hm = new HashMap<>();
        for(String str:strings){
            String key = getVal(str);
            List<String> lst = hm.getOrDefault(key, new ArrayList<>());
            lst.add(str);
            hm.put(key, lst);
        }
        List<List<String>> ret = new ArrayList<>();
        for(List<String> strGroup : hm.values()){
            ret.add(strGroup);
        }
        return ret;
    }

    String getVal(String s){
        StringBuilder val = new StringBuilder();
        for(int i =0; i<s.length();i++){
            if(i ==0){
                val.append("0");
            } else {
                val.append("#");
                if(s.charAt(i)>=s.charAt(i-1)){
                    val.append(Integer.toString(s.charAt(i) - s.charAt(i-1)));
                } else {
                    val.append(Integer.toString(26+s.charAt(i) - s.charAt(i-1)));
                }
            }
        }
        return val.toString();
    }
}
