package DynamicProgramming;

/*
https://leetcode.com/problems/edit-distance/
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 */
/*
Running time is O(n*m)
Space needed is O(n*m)
where n-> length of word1 and m->length of word2
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int[][] minDist = new int[word1.length()+1][word2.length()+1];
        for(int i = 1; i<=word1.length(); i++){
            minDist[i][0] = i;
        }
        for(int j = 1; j<=word2.length(); j++){
            minDist[0][j] = j;
        }

        for(int i = 1; i<=word1.length(); i++){
            for(int j = 1; j<=word2.length(); j++){
                minDist[i][j] = Integer.MAX_VALUE;
                minDist[i][j] = Math.min(minDist[i][j], minDist[i-1][j-1] + ((word1.charAt(i-1)== word2.charAt(j-1))?0:1));
                minDist[i][j] = Math.min(minDist[i][j], minDist[i-1][j] + 1);//deletion
                minDist[i][j] = Math.min(minDist[i][j], minDist[i][j-1] + 1);//addition
            }
        }
        return minDist[word1.length()][word2.length()];
    }
}
