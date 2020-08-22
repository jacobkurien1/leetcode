package Recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/strobogrammatic-number-ii/
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]
 */
/*
Running time is O(5^(N/2))
Space is also O(5^(N/2)) to store all outputs
 */
public class StrobogrammaticNumbersOfLength {
    public List<String> findStrobogrammatic(int n) {
        HashMap<Character, Character> hm = new HashMap<>();
        hm.put('1', '1');
        hm.put('6', '9');
        hm.put('0', '0');
        hm.put('9', '6');
        hm.put('8', '8');

        List<String> ret = new ArrayList<>();
        createStrobogrammatic(new char[n], 0, hm, ret);
        return ret;
    }

    void createStrobogrammatic(char[] charArray, int index, HashMap<Character, Character> hm, List<String> ret){
        if(index == Math.ceil(charArray.length/2.0)){
            ret.add(new String(charArray));
            return;
        }
        for(Map.Entry<Character, Character> entry:hm.entrySet()){
            if(index == 0 && entry.getKey() == '0' && charArray.length != 1){
                continue;
            }
            if(charArray.length%2 !=0 && index == charArray.length/2){
                if(entry.getKey() != entry.getValue()){
                    continue;
                }
            }
            charArray[index] = entry.getKey();
            charArray[charArray.length-1-index] = entry.getValue();
            createStrobogrammatic(charArray, index+1, hm, ret);
        }
    }
}
