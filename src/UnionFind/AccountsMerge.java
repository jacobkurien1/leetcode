package UnionFind;

import java.util.*;

/*
https://leetcode.com/problems/accounts-merge/
Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts.
Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name,
and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input:
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation:
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].
 */
/*
Running time is O(AlogA + alpha(A)) == O(AlogA) AlogA due to sorting requirement alph(A) due to union find with path compression and rank optimization
Space needed is O(A) where A = all emails given
 */
public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, Integer> emailId = new HashMap<>();
        HashMap<String, String> emailByName = new HashMap<>();
        int emailCount = 0;
        for(List<String> acct: accounts){
            for(int i = 1; i<acct.size();i++){
                if(!emailId.containsKey(acct.get(i))){
                    emailId.put(acct.get(i), emailCount++);
                }
                emailByName.put(acct.get(i), acct.get(0));
            }
        }
        UnionFind uf = new UnionFind(emailCount);
        for(List<String> acct: accounts){
            for(int i = 2; i<acct.size();i++){
                uf.union(emailId.get(acct.get(1)), emailId.get(acct.get(i)));
            }
        }

        HashMap<Integer, TreeSet<String>> hm = new HashMap<>();
        for(List<String> acct: accounts){
            for(int i = 1; i<acct.size();i++){
                int root = uf.find(emailId.get(acct.get(i)));
                TreeSet<String> emailSet = hm.getOrDefault(root, new TreeSet<>());
                emailSet.add(acct.get(i));
                hm.put(root, emailSet);
            }
        }
        List<List<String>> ret = new ArrayList<>();
        for(Set<String> emailSet: hm.values()){
            List<String> currSet = new ArrayList<>();

            for(String email:emailSet){
                if(currSet.isEmpty()){
                    currSet.add(emailByName.get(email));
                }
                currSet.add(email);
            }
            ret.add(currSet);
        }
        return ret;
    }

    class UnionFind{
        int[] parent;
        int[] rank;
        public UnionFind(int size){
            parent  = new int[size];
            rank = new int[size];
            for(int i = 0; i<size; i++){
                parent[i] =i;
            }
        }

        int find(int val){
            if(val != parent[val]){
                parent[val] = find(parent[val]);
            }
            return parent[val];
        }

        void union(int i, int j){
            int parentI = find(i);
            int parentJ = find(j);
            if(rank[parentI]>rank[parentJ]){
                parent[parentJ] = parentI;
            } else {
                parent[parentI] = parentJ;
                if(rank[parentI] == rank[parentJ]){
                    rank[parentJ] += 1;
                }
            }
        }
    }
}
