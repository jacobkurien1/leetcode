package StringMatching;

import java.util.HashSet;

/*
https://leetcode.com/problems/shortest-way-to-form-string/
From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.



Example 1:

Input: source = "abc", target = "abcbc"
Output: 2
Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
Example 2:

Input: source = "abc", target = "acdbc"
Output: -1
Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.
Example 3:

Input: source = "xyz", target = "xzyxz"
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".


Constraints:

Both the source and target strings consist of only lowercase English letters from "a"-"z".
The lengths of source and target string are between 1 and 1000.
 */
public class TransformOneToAnotherByDeletingChars {

    /*
    Algo: optimal
    This is a greedy approach and a variation on the KMP string matching algorithm
    Running time is O(source.length + target.length())
    source.length to create the charNext table
    target.length while doing the character matches
    Space needed is O(n)
     */
    public int shortestWay(String source, String target){
        if(source.length() == 0 || target.length()== 0){
            return -1;
        }
        //charNext['b'-'a'][2] tells which index 'b' is present in [2, source.length)
        int[][] charNext = new int[26][source.length()];
        //initialize with -1
        for(int i = 0;i<charNext.length; i++){
            for(int j = 0; j<source.length(); j++){
                charNext[i][j] = -1;
            }
        }
        for(int i = 0; i<source.length(); i++){
            for(int j = i; j>=0 && charNext[source.charAt(i)-'a'][j] == -1; j--){
                charNext[source.charAt(i)-'a'][j] = i;
            }
        }

        int countSource = 1;
        int sIndex = 0;
        int tIndex = 0;
        while(tIndex < target.length()){
            if(sIndex== source.length()){
                countSource++;
                sIndex = 0;
            }
            if(charNext[target.charAt(tIndex)-'a'][0] == -1){
                return -1;
            }
            if(charNext[target.charAt(tIndex)-'a'][sIndex] == -1){
                sIndex = source.length();
            } else {
                sIndex = charNext[target.charAt(tIndex)-'a'][sIndex] +1;
                tIndex++;
            }
        }
        return countSource;
    }

    /*
    Algo: brute force
    Running time O(source.length()* target.length())
    Space needed is O(1) as the input is limited to the 26 different characters(a-z)
     */
    public int shortestWayAlgo1(String source, String target) {
        if(source.length() == 0 || target.length()== 0){
            return -1;
        }
        HashSet<Character> sSet = new HashSet<>();
        for(int i = 0; i<source.length(); i++){
            sSet.add(source.charAt(i));
        }
        int countSource = 1;
        int sIndex = 0;
        int tIndex = 0;
        while(tIndex < target.length()){
            if(sIndex== source.length()){
                countSource++;
                sIndex = 0;
            }
            if(!sSet.contains(target.charAt(tIndex))){
                return -1;
            }
            if(source.charAt(sIndex) == target.charAt(tIndex)){
                tIndex++;
            }
            sIndex++;
        }
        return countSource;
    }
}
