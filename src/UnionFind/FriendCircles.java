package UnionFind;

/*
https://leetcode.com/problems/friend-circles/
There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature.
For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C.
And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1,
then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:
Input:
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input:
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.
 */
/*
Running time is O(n^2)
Since unionfind with path compression and rank optimization will take O(1) on average.
Note: The running time will be alph(n) known as inverse Auckermann function and has a value alpha(n)<5 for any n
Space needed is O(n)
 */
public class FriendCircles {
    public int findCircleNum(int[][] M) {
        if(M.length == 0){
            return 0;
        }
        UnionFind uf = new UnionFind(M.length);
        for(int i = 0; i<M.length;i++){
            for(int j = i+1; j<M[0].length;j++){
                if(M[i][j] == 1){
                    uf.union(i, j);
                }
            }
        }
        return uf.totalSets;
    }
    class UnionFind{
        int[] parent;
        int[] rank;
        int totalSets;
        UnionFind(int size){
            parent = new int[size];
            rank = new int[size];
            for(int i =0; i<size; i++){
                parent[i] = i;
            }
            totalSets = size;
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
            if(parentI == parentJ){
                return;
            }
            totalSets--;
            if(rank[parentI] > rank[parentJ]){
                parent[parentJ] = parentI;
            } else {
                parent[parentI] = parentJ;
                if(rank[parentI] == rank[parentJ]){
                    rank[parentJ] +=1;
                }
            }
        }
    }
}
