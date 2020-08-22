package UnionFind;

/*
https://leetcode.com/problems/similar-string-groups/
Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.
Notice that "tars" and "arts" are in the same group even though they are not similar.
 Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups are there?



Example 1:

Input: A = ["tars","rats","arts","star"]
Output: 2


Constraints:

1 <= A.length <= 2000
1 <= A[i].length <= 1000
A.length * A[i].length <= 20000
All words in A consist of lowercase letters only.
All words in A have the same length and are anagrams of each other.
The judging time limit has been increased for this question.
 */
/*
Running time is O(n^2*W) where n is the number of strings and W is the length of the string
Space needed is O(n)
 */
public class SimilarStringGroups {
    public int numSimilarGroups(String[] A) {
        UnionFind uf = new UnionFind(A.length);
        for(int i=0; i<A.length; i++){
            for(int j = i+1; j<A.length; j++){
                if(isSimilar(A[i], A[j])){
                    uf.union(i, j);
                }
            }
        }
        return uf.components;
    }

    class UnionFind{
        int[] parent;
        int[] rank;
        int components;
        UnionFind(int size){
            parent = new int[size];
            rank = new int[size];
            for(int i =0; i<size; i++){
                parent[i] = i;
            }
            components = size;
        }

        int find(int i){
            if(parent[i] != i){
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        void union(int i, int j){
            int parentI = find(i);
            int parentJ = find(j);
            if(parentI != parentJ){
                components--;
            }
            if(rank[parentI] > rank[parentJ]){
                parent[parentJ] = parentI;
            } else {
                parent[parentI] = parentJ;
                if(rank[parentI] == rank[parentJ]){
                    rank[parentJ] += 1;
                }
            }
        }
    }

    boolean isSimilar(String str1, String str2){
        if(str1.length() != str2.length()){
            return false;
        }
        char[] diff = new char[2];
        int mismatchCount =0;
        for(int i = 0; i<str1.length(); i++){
            if(str1.charAt(i) != str2.charAt(i)){
                mismatchCount++;
                if(mismatchCount>2){
                    return false;
                } else if(mismatchCount == 1){
                    diff[0] = str1.charAt(i);
                    diff[1] = str2.charAt(i);
                } else {
                    if(diff[0] != str2.charAt(i) || diff[1] != str1.charAt(i)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
