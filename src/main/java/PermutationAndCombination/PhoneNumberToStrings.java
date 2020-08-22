package PermutationAndCombination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
https://leetcode.com/problems/letter-combinations-of-a-phone-number
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
/*
Running time is O(3^N * 4^M) where N is the count of numbers which have 3 character associations
and M is the count of numbers which have 4 character associations
Space is also O(3^N * 4^M) as that much space will be used to store the answer.
 */
public class PhoneNumberToStrings {
    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.equals("")){
            return new ArrayList<String>();
        }
        HashMap<Character, String> hm = new HashMap<>();
        hm.put('0', "0");
        hm.put('1',"1");
        hm.put('2', "abc");
        hm.put('3', "def");
        hm.put('4', "ghi");
        hm.put('5', "jkl");
        hm.put('6', "mno");
        hm.put('7', "pqrs");
        hm.put('8', "tuv");
        hm.put('9', "wxyz");

        ArrayList<String> ret = new ArrayList<String>();
        ret.add("");
        for(int i = 0; i<digits.length(); i++){
            ArrayList<String> newList = new ArrayList<String>();
            for(String s : ret){
                String charsForDigit = hm.get(digits.charAt(i));
                for(int j = 0; j<charsForDigit.length(); j++){
                    newList.add(s + Character.toString(charsForDigit.charAt(j)));
                }
            }
            ret = newList;
        }
        return ret;
    }
}
