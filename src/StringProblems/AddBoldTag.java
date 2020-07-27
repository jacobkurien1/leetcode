package StringProblems;

/*
https://leetcode.com/problems/add-bold-tag-in-string/
Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict.
If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag.
Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
Example 1:

Input:
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"


Example 2:

Input:
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"


Constraints:

The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].
Note: This question is the same as 758: https://leetcode.com/problems/bold-words-in-string/
 */
/*
Running time is O(s.length()*dict.length*avg_width_of_dict_word)
Space needed is O(s.length())
 */
public class AddBoldTag {
    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for(int i = 0;i<s.length(); i++){
            int maxLen = 0;
            for(String dictWord: dict){
                if(s.startsWith(dictWord, i)){
                    maxLen = Math.max(maxLen, dictWord.length());
                }
            }
            for(int j = i; j<i+maxLen; j++){
                bold[j] = true;
            }
        }
        StringBuilder withBold = new StringBuilder();
        for(int i = 0;i<s.length(); i++){
            if(bold[i] && (i-1<0|| !bold[i-1])){
                withBold.append("<b>");
            }
            withBold.append(Character.toString(s.charAt(i)));
            if(bold[i] && (i+1==s.length() || !bold[i+1])){
                withBold.append("</b>");
            }
        }
        return withBold.toString();
    }
}
