package main.java.PermutationAndCombination;

import java.util.*;
/*
https://leetcode.com/problems/analyze-user-website-visit-pattern/
We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].

A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)

Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.



Example 1:

Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10],
website = ["home","about","career","home","cart","maps","home","home","about","career"]
Output: ["home","about","career"]
Explanation:
The tuples in this example are:
["joe", 1, "home"]
["joe", 2, "about"]
["joe", 3, "career"]
["james", 4, "home"]
["james", 5, "cart"]
["james", 6, "maps"]
["james", 7, "home"]
["mary", 8, "home"]
["mary", 9, "about"]
["mary", 10, "career"]
The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.


Note:

3 <= N = username.length = timestamp.length = website.length <= 50
1 <= username[i].length <= 10
0 <= timestamp[i] <= 10^9
1 <= website[i].length <= 10
Both username[i] and website[i] contain only lowercase characters.
It is guaranteed that there is at least one user who visited at least 3 websites.
No user visits two websites at the same time.
 */
/*
the running time is O(n^3) for the subset due to pruning at length == 3
to visualize this, think of the way you would do this iteratively.
public Set<String> subseqence(List<String> list){
        int n = list.size();
        Set<String> res = new HashSet<>();
        for (int i = 0; i < n-2; i++){
            for (int j = i+1; j < n-1; j++){
                for (int k = j+1; k < n; k++){
                    res.add(list.get(i)+","+list.get(j)+","+list.get(k));
                }
            }
        }
        return res;
    }

The space needed is equivalent to the space to store all the result and will be n^3.

 */
public class UserWebsitePattern {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, Integer> visitedPattern = new TreeMap<>();
        Map<String, UserAct> users  = new HashMap<>();

        int n = username.length;
        for(int i =0; i<n; i++) {
            if(!users.isEmpty() && users.containsKey(username[i])) {
                users.get(username[i]).add(timestamp[i], website[i]);
            } else{
                users.put(username[i], new UserAct(username[i], timestamp[i], website[i]));
            }
        }

        int maxFreq = 0;
        for (UserAct user: users.values()){
            for(String threeSeq: user.get3Seq()){
                int freq = visitedPattern.getOrDefault(threeSeq, 0);
                visitedPattern.put(threeSeq, freq+1);
                maxFreq = Math.max(maxFreq, freq+1);
            }
        }

        for(Map.Entry<String, Integer> entry: visitedPattern.entrySet()){
            if(entry.getValue() == maxFreq){
                return Arrays.asList(entry.getKey().split("#"));
            }
        }

        return new ArrayList<>();
    }

    class UserAct{
        String name;
        TreeMap<Integer, String> websites;
        UserAct (String user,int timestamp, String website){
            this.name = user;
            this.websites = new TreeMap<>();
            add(timestamp, website);
        }

        void add(int timestamp, String website){
            this.websites.put(timestamp, website);
        }

        Set<String> get3Seq(){
            threeSeq = new HashSet<String>();
            computeAll3Seq(new ArrayList<>(), 0, new ArrayList<>(websites.values()));
            return threeSeq;
        }

        private Set<String> threeSeq;

        private void computeAll3Seq(List<String> currSeq, int index, List<String> websitesInOrder){
            if(currSeq.size() == 3){
                threeSeq.add(String.join("#", currSeq));
                return;
            }
            if (index>=websitesInOrder.size()){
                return;
            }
            computeAll3Seq(currSeq, index+1, websitesInOrder);
            List<String> clone = new ArrayList<>(currSeq);
            clone.add(websitesInOrder.get(index));
            computeAll3Seq(clone, index+1, websitesInOrder);
        }
    }
}
