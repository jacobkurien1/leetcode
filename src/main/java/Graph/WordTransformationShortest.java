package main.java.Graph;

import java.util.*;

/*
https://leetcode.com/problems/word-ladder-ii/
Given two words (beginWord and endWord), and a dictionary's word list,
find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
/*
Running time is same as for BFS, in worst case O(V+E) ~ O(n + n^2) ~O(n^2). where n is the number of elements in wordlist
Space needed is also O(n^2) as we can save all the edges in the worst case.
 */
public class WordTransformationShortest {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, List<String>> parent = new HashMap<>();
        Queue<String> level = new LinkedList<>();
        level.add(beginWord);
        parent.put(beginWord, new ArrayList<>());
        boolean reachedEnd = false;
        List<List<String>> allPaths = new ArrayList<>();
        HashSet<String> wordSet = new HashSet<>(wordList);

        while(!level.isEmpty() && !reachedEnd){
            int levelLen = level.size();
            HashMap<String, List<String>> levelParent = new HashMap<>();
            for(int i = 0; i<levelLen; i++){
                String curr = level.poll();
                for(String next: getNextLevel(curr, wordSet)){
                    if(next.equals(endWord)){
                        //addPath.add(getCurrPath(parent, endWord));
                        reachedEnd = true;
                    }
                    if(parent.containsKey(next)){
                        continue;
                    }
                    List<String> parentsList = levelParent.getOrDefault(next, new ArrayList<>());
                    parentsList.add(curr);
                    levelParent.put(next, parentsList);
                    if(parentsList.size() == 1){
                        level.add(next); // make sure we dont add same word twice(in one level)
                    }
                }
            }
            copyBack(parent, levelParent);
        }
        return reachedEnd ? getAllPaths(parent, endWord) : new ArrayList<>();
    }

    void copyBack(HashMap<String, List<String>> parent, HashMap<String, List<String>> levelParent){
        parent.putAll(levelParent);
    }

    List<List<String>> getAllPaths(HashMap<String, List<String>> parent, String endWord){
        List<List<String>> ret = new LinkedList<>();
        if(parent.get(endWord).isEmpty()){
            List<String> baseCase = new LinkedList<>();
            baseCase.add(endWord);
            ret.add(baseCase);
        }
        for(String p : parent.get(endWord)){
            List<List<String>> paths = getAllPaths(parent, p);
            for(List<String> path: paths){
                path.add(endWord);
                ret.add(path);
            }
        }
        return ret;
    }

    List<String> getNextLevel(String curr, HashSet<String> wordList){
        List<String> nextLevel = new ArrayList<>();
        for(int i = 0; i<curr.length(); i++){
            for(char replace = 'a'; replace<='z'; replace++){
                String replacedStr = getReplacedStr(curr, i, replace);
                if(wordList.contains(replacedStr)){
                    nextLevel.add(replacedStr);
                }
            }
        }
        return nextLevel;
    }

    String getReplacedStr(String curr, int index, char replace){
        return curr.substring(0, index) + Character.toString(replace) + ((index==curr.length()-1)?"":curr.substring(index+1, curr.length()));
    }
}
