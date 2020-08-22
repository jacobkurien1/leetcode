package stack;

import java.util.Stack;

/**
 https://leetcode.com/problems/decode-string/
 Given an encoded string, return its decoded string.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

 You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

 Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 Examples:

 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

 **/

/** Solution
The idea is to use a stack and whenever we encounter a number push DecodedString object with the repeat variable as the number.
 And when a text character is encountered, then append it to the stringbuilder on the top of the stack.
 Once ']' is encountered, pop the stack, create the string by repeating the string in stringbuilder (repeat times) and
 append the results to the stringbuilder in the top of the stack.
 */
public class DecodeString {
    public String decodeString(String s) {
        Stack<DecodedString> st = new Stack();
        st.push(new DecodedString(0));
        for(int i=0; i<s.length(); i++){
            if(isNum(s.charAt(i)) && (i-1<0 || !isNum(s.charAt(i-1)))){
                st.push(new DecodedString(Character.getNumericValue(s.charAt(i))));
            }else if(isNum(s.charAt(i)) && i-1>=0 && isNum(s.charAt(i-1))){
                st.peek().repeat = st.peek().repeat*10 + Character.getNumericValue(s.charAt(i));
            } else if(isText(s.charAt(i))){
                st.peek().sb.append(s.charAt(i));
            } else if(s.charAt(i) ==']'){
                DecodedString ds = st.pop();
                String dsStr = ds.sb.toString();
                for(int j = 0; j<ds.repeat; j++){
                    st.peek().sb.append(dsStr);
                }
            }
        }
        return st.pop().sb.toString();
    }

    boolean isNum(char c){
        return c >= '0' && c<='9';
    }

    boolean isText(char c){
        return c >= 'a' && c<='z' || c >= 'A' && c<='Z' ;
    }

    class DecodedString{
        int repeat;
        StringBuilder sb;
        public DecodedString(int repeat){
            this.repeat = repeat;
            sb = new StringBuilder();
        }
    }
}
