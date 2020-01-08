package StringProblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
https://leetcode.com/problems/brace-expansion/
A string S represents a list of words.

Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.
If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].

Return all words that can be formed in this manner, in lexicographical order.



Example 1:

Input: "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]
Example 2:

Input: "abcd"
Output: ["abcd"]


Note:

1 <= S.length <= 50
There are no nested curly brackets.
All characters inside a pair of consecutive opening and ending curly brackets are different.
 */
/*
Running time is O(2^(n/2)), need to verify this.
Running time is found by having a list which has a {1,2}{3,4}{5,6}{7,8}{9,10} -> O(2^5)
 */
public class BraceExpansion {
    public String[] expand(String S) {
        List<String> ret = new ArrayList<String>();
        ret.add("");
        List<String> clone = null;
        for(int i = 0; i<S.length(); i++){
            if(S.charAt(i) == '{'){
                clone = new ArrayList<String>();
            } else if(S.charAt(i) == '}'){
                ret = clone;
                clone = null;
            } else if(S.charAt(i) != ',') {
                if(clone != null){
                    // braces was opened before
                    for(String str : ret){
                        clone.add(str+Character.toString(S.charAt(i)));
                    }
                } else {
                    clone = new ArrayList<String>();
                    for(String str : ret){
                        clone.add(str+Character.toString(S.charAt(i)));
                    }
                    ret = clone;
                    clone = null;
                }
            }
        }
        Collections.sort(ret);
        String[] retArr = new String[ret.size()];
        return ret.toArray(retArr);
    }
}
