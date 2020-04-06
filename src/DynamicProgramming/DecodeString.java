package DynamicProgramming;

/*
https://leetcode.com/problems/decode-ways/
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
/*
Running time is O(N)
Space is O(1)
 */
public class DecodeString {
    public int numDecodings(String s) {
        int prev1 = 1;
        int prev2 = 1;
        for(int i = 0; i<s.length(); i++){
            int curr;
            if(s.charAt(i)=='0'){
                curr = 0;
            }else {
                curr = prev1;
            }
            if(i-1>=0 && isBetween1And26(s.charAt(i-1), s.charAt(i))){
                curr += prev2;
            }
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    boolean isBetween1And26(char c1, char c2){
        if(c1 == '0'){
            return false;
        }
        int val = (int)(c1 - '0');
        val*=10;
        val += (int)(c2-'0');
        return val<=26;
    }
}
