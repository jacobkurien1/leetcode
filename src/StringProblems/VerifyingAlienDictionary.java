package StringProblems;

import java.util.HashMap;
/*
https://leetcode.com/problems/verifying-an-alien-dictionary/
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order.
The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet,
return true if and only if the given words are sorted lexicographicaly in this alien language.



Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.)
According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
 */
/*
Running time is O(max length of the word in words array)
Space needed is O(1) as the hashmap will not be of size greater than 26
 */
public class VerifyingAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for(int i = 0; i<order.length(); i++){
            hm.put(order.charAt(i), i);
        }
        for(int i = 1; i<words.length ; i++){
            int index = 0;
            String prev = words[i-1];
            String curr = words[i];
            while(index<prev.length() && index<curr.length() && prev.charAt(index)==curr.charAt(index) ){
                index++;
            }
            if(index==prev.length() || index == curr.length() || hm.get(prev.charAt(index)) >hm.get(curr.charAt(index))){
                return false;
            }
        }
        return true;
    }
}
