package main.java.StringProblems;

/*
https://leetcode.com/problems/find-the-difference/
Given two strings s and t which consist of only lowercase letters.

String t is generated by random shuffling string s and then add one more letter at a random position.

Find the letter that was added in t.

Example:

Input:
s = "abcd"
t = "abcde"

Output:
e

Explanation:
'e' is the letter that was added.
 */
/*
Running time is O(s.length())
Space needed is O(1)
 */
public class FindDifference {
    public char findTheDifference(String s, String t) {
        int val = t.charAt(t.length()-1);
        for(int i = 0; i<s.length(); i++){
            val += t.charAt(i);
            val -= s.charAt(i);
        }
        return (char)val;
    }
}
