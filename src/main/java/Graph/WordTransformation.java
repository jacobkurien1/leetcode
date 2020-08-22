package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
https://leetcode.com/problems/word-ladder/
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
/*
We will do a BFS here to get the min path of transformation
Running time = O(N*M) N is the number of elements in dictionary,
M is the size of the word

Space needed is O(N) to save the transformations (present in the dictionary)
 */
public class WordTransformation {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        List<String> curr = new ArrayList<String>();
        HashSet<String> visited = new HashSet<>();
        HashSet<String> dict = new HashSet<>(wordList);
        curr.add(beginWord);
        int transformLen = 1;
        while(!curr.isEmpty()){
            List<String> next = new ArrayList<>();
            for(String word : curr){
                List<String> nextWords = getNextWords(word, visited, dict);
                next.addAll(nextWords);
                visited.addAll(nextWords);
                if(visited.contains(endWord)){
                    return transformLen+1;
                }
            }
            curr = next;
            transformLen++;
        }
        return 0;
    }

    List<String> getNextWords(String s, HashSet<String> visited, HashSet<String> dict){
        List<String> newWords = new ArrayList<>();
        for(int i = 0; i<s.length(); i++){
            char[] cArr = s.toCharArray();
            for(char aToZ = 'a'; aToZ < 'z'; aToZ++ ){
                cArr[i] = aToZ;
                String newWord = new String(cArr);
                if(!visited.contains(newWord) && dict.contains(newWord)){
                    newWords.add(newWord);
                }
            }
        }
        return newWords;
    }
}
