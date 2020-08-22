package HashMapProblems;

import java.util.HashMap;

/*
https://leetcode.com/problems/first-unique-character-in-a-string/
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
 */
/*
Running time is O(n)
Space needed is O(n)
 */
public class FirstUniqueCharacter {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> freq = new HashMap<>();
        for(int i = 0; i<s.length(); i++){
            int charFreq = freq.getOrDefault(s.charAt(i), 0);
            freq.put(s.charAt(i), charFreq+1);
        }
        for(int i = 0; i<s.length(); i++){
            if(freq.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }
}
