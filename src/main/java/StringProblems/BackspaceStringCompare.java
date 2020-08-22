package StringProblems;

/*
https://leetcode.com/problems/backspace-string-compare/
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Note that after backspacing an empty text, the text will continue empty.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:

Can you solve it in O(N) time and O(1) space?
 */
/*
Running time is O(N)
Space needed is O(1)
 */
public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        int sIndex = S.length()-1;
        int tIndex = T.length()-1;

        while(sIndex>=0 || tIndex>=0){
            while(sIndex >=0 && S.charAt(sIndex) == '#'){
                sIndex =getNextIndex(S, sIndex);
            }
            while(tIndex >=0 && T.charAt(tIndex) == '#'){
                tIndex = getNextIndex(T, tIndex);
            }
            if(sIndex>=0 && tIndex>=0){
                if(S.charAt(sIndex)!= T.charAt(tIndex)){
                    return false;
                }
                sIndex--;tIndex--;
            } else {
                break;
            }
        }
        return sIndex == -1 && tIndex ==-1;
    }

    int getNextIndex(String str, int currIndex){
        if(str.charAt(currIndex) != '#'){
            return currIndex;
        }
        int charCountToDelete = 1;
        currIndex--;
        while(charCountToDelete>0 && currIndex >= 0){
            if(str.charAt(currIndex) == '#'){
                charCountToDelete++;
            } else {
                charCountToDelete--;
            }
            currIndex--;
        }
        return currIndex;
    }
}
