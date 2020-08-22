package ArraysProblem;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/text-justification
Given an array of words and a width maxWidth,
 format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' '
when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words,
the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
 */
/*
Running time is O(n * maxWidth)
Space needed is O(n);
 */
public class TextJustificationGreedy {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> justText = new ArrayList<>();
        int st = 0;
        int end = 0;

        while(st < words.length){
            int spaceNeeded = 0;
            List<String> oneLine = new ArrayList<>();
            int charSpaceNeeded = 0;

            // Get words that go in a line
            while(end < words.length){
                spaceNeeded += words[end].length();
                if(spaceNeeded <=maxWidth){
                    oneLine.add(words[end]);
                    charSpaceNeeded += words[end].length();
                } else {
                    break;
                }
                spaceNeeded++; // addition of space at the end of word
                end++;
            }

            // Create the line:
            int whitespacesTotal = maxWidth - charSpaceNeeded;
            int whitespacePerWord = (oneLine.size() == 1)?0:(whitespacesTotal /(oneLine.size()-1));
            int extraWhitespaces = (oneLine.size() ==1)?0:(whitespacesTotal %(oneLine.size()-1));
            if(end == words.length){//last line
                whitespacePerWord = (whitespacePerWord == 0)?0:1;
                extraWhitespaces = 0;
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i<oneLine.size(); i++){
                sb.append(oneLine.get(i));
                if(i==0 || i != oneLine.size()-1){
                    for(int space = 0; space < whitespacePerWord; space++){
                        sb.append(" ");
                    }
                    if(extraWhitespaces != 0){
                        extraWhitespaces--;
                        sb.append(" ");
                    }
                }
            }
            while(sb.length() != maxWidth){//last line
                sb.append(" ");
            }
            justText.add(sb.toString());
            st = end;
        }
        return justText;
    }
}
