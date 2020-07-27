package ArraysProblem;
/*
https://leetcode.com/problems/shortest-distance-to-a-character/
Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.

Example 1:

Input: S = "loveleetcode", C = 'e'
Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]


Note:

S string length is in [1, 10000].
C is a single character, and guaranteed to be in string S.
All letters in S and C are lowercase.
 */
/*
Running time O(n)
Space is O(n)
 */
public class ShortestDistanceToCharacter {
    public int[] shortestToChar(String S, char C) {
        int[] shortestToChar = new int[S.length()];
        int lastIndex = -1;
        for(int i = 0; i<S.length(); i++){
            if(S.charAt(i) == C){
                lastIndex = i;
            }
            shortestToChar[i] = (lastIndex == -1)?Integer.MAX_VALUE:i-lastIndex;
        }
        lastIndex = -1;
        for(int i = S.length()-1; i>=0; i--){
            if(S.charAt(i) == C){
                lastIndex = i;
            }
            if(lastIndex != -1){
                shortestToChar[i] = Math.min(shortestToChar[i], lastIndex-i);
            }
        }
        return shortestToChar;
    }
}
