package StringProblems;

/*
https://leetcode.com/problems/reverse-words-in-a-string
Given an input string, reverse the string word by word.



Example 1:

Input: "the sky is blue"
Output: "blue is sky the"
Example 2:

Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:

Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.


Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.


Follow up:

For C programmers, try to solve it in-place in O(1) extra space.
 */
public class ReverseWordsInSentence {
    /*
    Algo using standard functions
    Running time O(n)
    Space is O(n)
     */
    public String reverseWords(String s) {
        s = s.trim();
        String[] parts = s.split("\\s+");

        StringBuilder sb = new StringBuilder();
        for(int i = parts.length-1; i>=0; i--){
            sb.append(parts[i]);
            if(i != 0){
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /*
    Algo2: Implementing lib functions
    Running time is O(n)
    Space is O(n)
     */
    public String reverseWordsAlgo2(String s) {
        if(s == null ){
            return s;
        }
        char[] charArr = s.toCharArray();
        reverse(charArr, 0, s.length()-1);
        int st = 0;
        int end = 0;
        int spaceCount = 1;
        StringBuilder sentence = new StringBuilder();
        StringBuilder word = new StringBuilder();
        while(end<s.length()){
            if(charArr[end] == ' '){
                if(spaceCount == 0){
                    spaceCount++;
                    if(word.length() != 0){
                        sentence.append(word.reverse().toString() + " ");
                        word = new StringBuilder();
                    }
                }
                st = end;
            } else {
                spaceCount = 0;
                word.append(charArr[end]);
            }
            end++;
        }
        if(word.length() != 0){
            sentence.append(word.reverse().toString());
        } else if(sentence.length() != 0) {
            sentence.setLength(sentence.length()-1);
        }

        return sentence.toString();
    }

    void reverse(char[] charArr, int st, int end){
        while(st<end){
            char temp = charArr[st];
            charArr[st] = charArr[end];
            charArr[end] = temp;
            end--;st++;
        }
    }
}
